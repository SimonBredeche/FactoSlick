package factorioMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.newdawn.slick.GameContainer;

public class Save {
	private String currentPathl1;
	private String currentPathl2;
	private String currentPathl3;
	private String playerDataPath;
	private String absolutePath = "save/";
	private GameContainer container;
	
	public Save(String slotName, GameContainer container) {
		this.absolutePath += slotName;
		this.currentPathl1 = this.absolutePath + "/layer1.txt";
		this.currentPathl2 = this.absolutePath + "/layer2.txt";
		this.currentPathl3 = this.absolutePath + "/layer3.txt";
		this.playerDataPath = this.absolutePath + "/playerData.txt";
		this.container = container;
		
	}
	public static void delete(String dirPath) {
        File file = new File(dirPath); 
        
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
	}
    public void unCompress() throws IOException {
        String fileZip = absolutePath + ".zip";
        File destDir = new File(absolutePath);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
    
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        
        return destFile;
    }
    public static void compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        String zipFileName = dirPath.concat(".zip");
        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public void saveGame(WorldGeneration world, AssetManager asset, Camera camera, ScienceHUD science) {
		try {
			FileWriter myWriter3 = new FileWriter(this.currentPathl3);
			FileWriter myWriter2 = new FileWriter(this.currentPathl2);
			FileWriter myWriter = new FileWriter(this.currentPathl1);
			for(int i = 0; i < (world.getMapWidht()-1)*world.getChunksize(); i++) {
				for(int j = 0; j < (world.getMapHeight()-1)*world.getChunksize(); j++) {
					myWriter.write(world.getTileFromChunk(j, i).getName() + "_" + world.getTileFromChunk(j, i).getAngle() +"|"); // THIRD LAYER
					myWriter2.write(world.getTileFromChunkl1(j, i).getName() + "|"); //FIRST LAYER
					if(world.getTileFromChunkl2(j, i).getTag() == "ore") {
						myWriter3.write(world.getTileFromChunkl2(j, i).getName() +"_" + ((Iron)world.getTileFromChunkl2(j, i)).getDurability() +"|");
					}
					else {
						myWriter3.write(world.getTileFromChunkl2(j, i).getName() + "|"); // SECOND LAYER
					}
					
				}
			}
			this.savePlayerData(asset.getIteminventory(), camera, science);
			myWriter.close();
			myWriter2.close();
			myWriter3.close();
			compress(absolutePath);
			delete(this.currentPathl1);
			delete(this.currentPathl2);
			delete(this.currentPathl3);
			delete(this.playerDataPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void savePlayerData(ItemInventory inventory, Camera camera, ScienceHUD science) throws IOException {
		FileWriter myWriter = new FileWriter(this.playerDataPath);
		for(ItemSlot i : inventory.getStoredTiles()) {
			myWriter.write(i.getName() + "_" + i.getNumber() + "|");
			
		}
		myWriter.write("\n");
		myWriter.write(Float.toString(camera.getxCam()));
		myWriter.write("\n");
		myWriter.write(Float.toString(camera.getyCam()));
		myWriter.write("\n");
		for(Science s : science.getAllScience()) {
			if(!s.isLocked()) {
				myWriter.write(s.getName() + "|");
			}
		}
		myWriter.close();
		
	}
	public void loadGame(WorldGeneration world , AssetManager asset,Camera camera, ScienceHUD science, Inventory inventory) {
		try {
			this.unCompress();
			this.loadLayerl1(currentPathl2, world, asset);
			this.loadLayerl3(currentPathl1, world, asset);
			this.loadLayerl2(currentPathl3, world, asset);
			this.LoadData(asset, camera, science, inventory);
			delete(this.currentPathl1);
			delete(this.currentPathl2);
			delete(this.currentPathl3);
			delete(this.playerDataPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void LoadData(AssetManager asset,Camera camera, ScienceHUD science, Inventory inventory) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(this.playerDataPath));
		String line;
		int nbline = 0;
		while ((line = in.readLine()) != null)
		{
			switch (nbline) {
			case 0: this.loadInventory(asset, line);break;
			case 1: camera.setxCam((Float.parseFloat(line))); break;
			case 2: camera.setyCam((Float.parseFloat(line))); break;
			case 3: this.loadScience(science, line, inventory); break;
			}
			nbline++;

		}
		in.close();
	}
	public void loadScience(ScienceHUD science, String line, Inventory inventory) {
		String sciencename = "";
	    for(int i = 0; i < line.length(); i++) { 	
			if(line.charAt(i) == '|') {				
				for(Science s : science.getAllScience()) {
					if(s.getName().equals(sciencename)){
						if(s.isLocked()) {
							s.setClicked(true);
							s.UnlockScience(inventory, this.container);
						}
					}

				}
				sciencename = "";
			}
		    else {
		    	sciencename += line.charAt(i);
		    }
		}
		
	}
	public void loadInventory(AssetManager asset, String s) {
		asset.getIteminventory().getStoredTiles().clear();
		String entityname = "";
		boolean angle = false;
		String number = "";
		for(int i = 0; i < s.length();i++) {
			if(s.charAt(i) == '|') {
				for(Entity e : asset.getAllEntity()) {
					if(e.getName().equals(entityname)) {
						ItemSlot ItemSlotTemp = new ItemSlot(e.getImage(), e.getName());
						ItemSlotTemp.setNumber(Integer.parseInt(number));
						asset.getIteminventory().addItemSlot(ItemSlotTemp);
					}
				}
				entityname = "";
				number = "";
				angle = false;
			}
			else if(s.charAt(i) == '_' || angle) {
				if(!angle) {
					angle = true;
					i++;
				}
				number += s.charAt(i);
			}
		    else {
		    	entityname += s.charAt(i);
		    }
		}
	}
	public void loadLayerl1(String name, WorldGeneration world, AssetManager asset) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(name));
			String line = "";
			int x = 0,y = 0;
			String tilename = "";
			while ((line = in.readLine()) != null)
			{	
			    for(int i = 0; i < line.length(); i++) { 	
					if(line.charAt(i) == '|') {
						world.setTileFromIndexl1(x, y, asset.loadTileByName(tilename));
						world.getTileFromChunkl1(x, y).setX(x);
						world.getTileFromChunkl1(x, y).setY(y);
						//System.out.println("Current tile :" + tilename + " X: "+ x + " Y: " + y);
						if(x < (world.getMapWidht()-1)*world.getChunksize()) {
							x++;
						}
						else
						{
							x = 1;
							if(y < (world.getMapHeight()-1)*world.getChunksize())
								y++;
						}
						tilename = "";
					}
				    else {
				    	tilename += line.charAt(i);
				    }
				}

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadLayerl3(String name, WorldGeneration world, AssetManager asset) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(name));
			String line = "";
			int x = 0,y = 0;
			String tilename = "";
			boolean angle = false;
			String dangle = "";
			while ((line = in.readLine()) != null)
			{	
				
			    for(int i = 0; i < line.length(); i++) { 	
					if(line.charAt(i) == '|') {
						try {
							world.setTileFromIndex(x, y, (Tile)asset.loadTileByName(tilename).clone());
							world.getTileFromChunk(x, y).setAngle(Integer.parseInt(dangle));
							world.getTileFromChunk(x, y).setX(x);
							world.getTileFromChunk(x, y).setY(y);
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("Current tile :" + tilename + " X: "+ x + " Y: " + y);
						if(x < (world.getMapWidht()-1)*world.getChunksize()) {
							x++;
						}
						else
						{
							x = 1;
							if(y < (world.getMapHeight()-1)*world.getChunksize())
								y++;
						}
						tilename = "";
						dangle = "";
						angle = false;
					}
					else if(line.charAt(i) == '_' || angle) {
						if(!angle) {
							angle = true;
							i++;
						}
						dangle += line.charAt(i);
					}
				    else {
				    	tilename += line.charAt(i);
				    }
				}

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadLayerl2(String name, WorldGeneration world, AssetManager asset) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(name));
			String line = "";
			int x = 0,y = 0;
			String tilename = "";
			boolean angle = false;
			String dangle = "";
			while ((line = in.readLine()) != null)
			{	
				
			    for(int i = 0; i < line.length(); i++) { 	
					if(line.charAt(i) == '|') {
						try {
							if(!tilename.equals("void") && !tilename.equals("oil")){
								Iron iron = (Iron) asset.loadTileByName(tilename);
								world.setTileFromIndexl2(x, y, (Tile)iron.clone());
								((Iron)world.getTileFromChunkl2(x, y)).setDurability(Integer.parseInt(dangle));
								world.getTileFromChunkl2(x, y).setX(x);
								world.getTileFromChunkl2(x, y).setY(y);
								
							}
							else {
								world.setTileFromIndexl2(x, y, asset.loadTileByName(tilename));
							}
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("Current tile :" + tilename + " X: "+ x + " Y: " + y);
						if(x < (world.getMapWidht()-1)*world.getChunksize()) {
							x++;
						}
						else
						{
							x = 1;
							if(y < (world.getMapHeight()-1)*world.getChunksize())
								y++;
						}
						tilename = "";
						dangle = "";
						angle = false;
					}
					else if(line.charAt(i) == '_' || angle) {
						if(!angle) {
							angle = true;
							i++;
						}
						dangle += line.charAt(i);
					}
				    else {
				    	tilename += line.charAt(i);
				    }
				}

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


}
