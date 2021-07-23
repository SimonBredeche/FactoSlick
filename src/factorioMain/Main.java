package factorioMain;








import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Main extends BasicGame{

	private AssetManager asset;
	private WorldGeneration world;
	private Camera camera;
	private boolean exit = false;
	private HUD hud;
	private Inventory inventory;
	private float xf = 1f;
	private boolean survival = true; //TRUE = SURVIVAL FALSE = CREATIVE
	private ChargingBar bar;
	private boolean rightclick = false;
	private ScienceHUD scienHUDtemp;
	private MainMenu menu;
	private GameHUD gamehud;
    public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws SlickException {
    	
        new AppGameContainer(new Main("Factorio"), 1920, 1080, false).start();
   
        
    }
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		container.setFullscreen(true); //FULLSCREEN
		container.setTargetFrameRate(80);
		container.setVSync(true);
		asset = new AssetManager();
		this.camera = new Camera();
		this.bar = new ChargingBar(asset.chargebarimg);
		world = new WorldGeneration(asset);
		this.hud = new HUD(asset);
		camera.setxCam((world.getChunksize()*world.getTileSize())*world.getMapWidht()/2);
		camera.setyCam((world.getChunksize()*world.getTileSize())*world.getMapHeight()/2);
		asset.cursor.tileSize = world.getTileSize();
		menu = new MainMenu(asset, container);
		gamehud = new GameHUD(asset,container);
		inventory = new Inventory(asset);
		inventory.addTiles(asset.Conveyor, container);
		//inventory.addTiles(asset.ConveyorII, container);
		inventory.addTiles(asset.Concrete, container);
		inventory.addTiles(asset.Miner, container);
		inventory.addTiles(asset.Furnace, container);
		inventory.addTiles(asset.inserter, container);
		inventory.addTiles(asset.gearmaker, container);
		inventory.addTiles(asset.circuitmaker, container);
		//inventory.addTiles(asset.framemaker, container);
		inventory.addTiles(asset.itemcollector, container);
		//inventory.addTiles(asset.longinserter, container);
		//inventory.addTiles(asset.GreenPotionMaker, container);
		inventory.addTiles(asset.RedPotionMaker, container);
		inventory.addTiles(asset.WireMaker, container);
		inventory.addTiles(asset.Oilpump, container);
		//inventory.addTiles(asset.MonitorMaker, container);
		//ITEM CHEAT
		/*for(int i = 0; i < 999; i++) {
			asset.getIteminventory().addItem(asset.RedPotion);
		}*/
		
		
		this.scienHUDtemp = new ScienceHUD(this.asset,asset.getIteminventory(),container,this.inventory);
		ScienceManager SMtemp = new ScienceManager();
		SMtemp.loadScience(asset, scienHUDtemp, container);

	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		// TODO Auto-generated method stub
		if(this.exit) {
			container.exit();
		}
		if(!this.menu.isOpen()) {
			g.scale(this.xf, this.xf);
			g.translate(container.getWidth() / 2 - (int) camera.getxCam(), 
	                container.getHeight() / 2 - (int) camera.getyCam());
			world.DrawWorldMap(g, 3, 2, Math.round((camera.getxCam()/world.getTileSize())/world.getChunksize()), Math.round((camera.getyCam()/world.getTileSize())/world.getChunksize()));
			asset.cursor.Draw(g);
			hud.Draw(g);
			bar.Draw(g);
			if(this.inventory.isOpen()) {
				this.inventory.Draw(g,container);
			}
			if(asset.getIteminventory().isOpen()) {
				asset.getIteminventory().Draw(g, container);
			}
			if(this.scienHUDtemp.isOpen()) {
				this.scienHUDtemp.Draw(g);
			}
			this.gamehud.DrawMenu(g, container);
		}
		else {
			this.menu.DrawMenu(g, container);
			if(this.menu.isCloseGame()) {
				this.exit = true;
			}
		}

	}
	public void updateBar() {
		if(this.rightclick && world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getName() != "void") {
			this.bar.setMaxTick(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getHardeness());
			if(this.bar.isReady() || !this.survival) {
				asset.cursor.setBreaking(true);
				this.bar.resetbar();
			}
			this.bar.setUpdate(true);
			this.bar.update();
		}
		else if(this.rightclick && world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getTag() == "ore") {
			this.bar.setMaxTick(world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getHardeness());
			if(this.bar.isReady() || !this.survival) {
				asset.cursor.setBreakingore(true);
				this.bar.resetbar();
			}
			this.bar.setUpdate(true);
			this.bar.update();
		}
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		//System.out.println(delta);
		this.camera.updateCam(world);
		this.world.UpdateWorlMap(3*10, 2*10, Math.round((camera.getxCam()/world.getTileSize())/world.getChunksize()), Math.round((camera.getyCam()/world.getTileSize())/world.getChunksize()),this.asset);
		this.world.UpdateEntity();
		this.updateBar();
		if(this.gamehud.isCloseGame()) {
			this.exit = true;
		}
		if(this.gamehud.getSelectedSave() != null) {
			this.gamehud.getSelectedSave().saveGame(this.world,this.asset, this.camera, this.scienHUDtemp);
			this.gamehud.setSelectedSave(null);
		}
		if(this.menu.getSelectedSave() != null) {
			this.menu.getSelectedSave().loadGame(world, asset,this.camera,this.scienHUDtemp, this.inventory);
			this.menu.setSelectedSave(null);
		}
		//CURSOR MANAGEMENT
		if(asset.cursor.getTileXIndex() > 0 && asset.cursor.getTileXIndex() < world.getChunksize()*world.getMapWidht() 
			&& asset.cursor.getTileYIndex() > 0 && asset.cursor.getTileYIndex() < world.getChunksize()*world.getMapHeight() && !inventory.isOpen()) {
			if(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getName() != "void") {
				hud.setInfo(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).toString()+ "\n" + "Seed : "+this.world.getSeed());
			}
			else if(world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getName() != "void") {
				hud.setInfo(world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).toString()+ "\n" + "Seed : "+this.world.getSeed());
			}
			else {
				hud.setInfo(world.getTileFromChunkl1(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).toString() + "\n" + "Seed : "+this.world.getSeed());
			}
			if(asset.cursor.isBreaking()) {
				asset.getIteminventory().addItem(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getEntity());
				world.setTileFromIndex(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex(), asset.voidt);
				if(this.survival)
					asset.cursor.setBreaking(false);
				
			}
			else if(asset.cursor.isBreakingore()) {
				if(world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getTag() == "ore") {
					Iron temp = ((Iron)world.getTileFromChunkl2(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()));
					asset.getIteminventory().addItem(temp.getEntity());
					temp.setDurability(temp.getDurability()-1);
					asset.cursor.setBreakingore(false);
				}
			}
			if(asset.cursor.isPlacing() && asset.cursor.getTile() != null) {
				if(/*world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getName() == asset.cursor.getTile().getName() || */
					world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getName() == "void") {
					try {
						if(this.survival) {
							if(((Tile)asset.cursor.getTile()).getCraft() != null) {
								if(((Tile)asset.cursor.getTile()).getCraft().ItemValid(asset.getIteminventory())) {
									world.setTileFromIndex(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex(), (Tile)asset.cursor.getTile().clone());
									((Tile)asset.cursor.getTile()).getCraft().removedItems(asset.getIteminventory());
								}
								else {
									asset.cursor.setPlacing(false);
								}
							}
						}
						else {
							world.setTileFromIndex(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex(), (Tile)asset.cursor.getTile().clone());
						}
						
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).setX(asset.cursor.getTileXIndex());
					world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).setY(asset.cursor.getTileYIndex());
				}
				if(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).isRotable()) {
					world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).setAngle(asset.cursor.getAngle());
				}
			}
		}
		asset.cursor.x = (Mouse.getX()-(container.getWidth() / 2 - (int) camera.getxCam()))-asset.cursor.tileSize/2;
		asset.cursor.y = ((container.getHeight() - (int)Mouse.getY())-(container.getHeight() / 2 - (int) camera.getyCam()))-asset.cursor.tileSize/2;

		
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		switch (button) {
			case Input.MOUSE_LEFT_BUTTON:
				asset.cursor.setPlacing(true);
				break;
			case Input.MOUSE_RIGHT_BUTTON:
				this.rightclick = true;
				break;
		}
	}
	@Override
	public void mouseReleased(int button, int x, int y){
		asset.cursor.setBreaking(false);
		asset.cursor.setPlacing(false);
		this.bar.setUpdate(false);
		this.bar.resetbar();
		this.rightclick = false;
		
		
	}
    @Override
    public void keyReleased(int key, char c) {
    	this.camera.east = false;
    	this.camera.west = false;
    	this.camera.south = false;
    	this.camera.north = false;
    }
    @Override
    public void keyPressed(int key, char c) {
    	switch (key) {
    		case Input.KEY_D:    			
    			this.camera.east = true;
    			break;
    		case Input.KEY_Q: 
    			this.camera.west = true;
    			break;
    		case Input.KEY_S:
    			this.camera.south = true;
    			break;
    		case Input.KEY_Z: 
    			this.camera.north = true;
    			break;
    		case Input.KEY_ESCAPE:
    			this.asset.cursor.setTile(null);
    			break;
    		case Input.KEY_T:
    			this.scienHUDtemp.setOpen(!this.scienHUDtemp.isOpen());
    			break;
    		case Input.KEY_R:
    			if(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).isRotable()) {
    				world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).setAngle(world.getTileFromChunk(asset.cursor.getTileXIndex(), asset.cursor.getTileYIndex()).getAngle()+90);
    			}
    			else {
    				asset.cursor.setAngle(asset.cursor.getAngle()+90);
    			}
    			break;
    		case Input.KEY_E:
    			this.inventory.setOpen(!this.inventory.isOpen());
    			break;
    		case Input.KEY_I:
    			this.asset.getIteminventory().setOpen(!this.asset.getIteminventory().isOpen());
    			break;
    		case Input.KEY_SPACE:
    			this.exit = true;
    			break;
    		case Input.KEY_SUBTRACT:
    			this.xf += 0.1;
    			break;
    		case Input.KEY_ADD:
    			if(this.xf > 1) {
    				this.xf -= 0.1;
    			}
    			break;
 
    	}

    }


}
