package factorioMain;


import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class WorldGeneration {
	Chunk[][] globalMap;
	Chunk[][] globalMapl2;
	Chunk[][] globalMapl3;
	private final int mapWidth = 100; //MAX : 500 
	private final int mapHeight = 100; //MAX : 500
	private int tileSize;
	private int chunksize;
	private long seed;
	private ArrayList<Entity> allEntity;
	private ArrayList<Entity> deleteEntity;
	public void AddEntity(Entity e) {
		this.allEntity.add(e);
	}
	public ArrayList<Entity> getAllEntity(){
		return this.allEntity;
	}
	public void reset() {
		for (int i = 0; i < this.mapWidth; i++) {
			for (int j = 0; j < this.mapHeight; j++) {
				globalMap[i][j].reset();
				globalMapl2[i][j].reset();
				globalMapl3[i][j].reset();
			}
				
		}
	}
	public WorldGeneration(AssetManager asset) {
		this.allEntity = new ArrayList<Entity>();
		this.deleteEntity = new ArrayList<Entity>();
		this.seed = (long) (Math.random()*(100000-100)+1)+100;
		long oreSeed = seed-25;
		Noise2D noise = new Noise2D(this.seed);
		Noise2D ironnoise = new Noise2D(oreSeed);
		oreSeed--;
		Noise2D coppernoise = new Noise2D(oreSeed);
		oreSeed--;
		Noise2D coalnoise = new Noise2D(oreSeed);
		Noise2D[] orenoise = new Noise2D[4];
		oreSeed *= 10;
		Noise2D TreeNoise = new Noise2D(oreSeed);
		oreSeed--;
		Noise2D oilnoise = new Noise2D(oreSeed);
		orenoise[0] = ironnoise; orenoise[1] = coppernoise; orenoise[2] = coalnoise; orenoise[3] = oilnoise;
		globalMap = new Chunk[this.mapWidth][this.mapHeight];
		globalMapl2 = new Chunk[this.mapWidth][this.mapHeight];
		globalMapl3 = new Chunk[this.mapWidth][this.mapHeight];
		for (int i = 0; i < this.mapWidth; i++) {
			for (int j = 0; j < this.mapHeight; j++) {
				globalMap[i][j] = new Chunk(asset, i, j, noise);
				globalMapl2[i][j] = new Chunk(asset,i,j, TreeNoise,globalMap[i][j]);
				globalMapl3[i][j] = new Chunk(asset,i,j,orenoise,globalMap[i][j]);
			}
				
		}
		tileSize = globalMap[0][0].getTileSize();
		setChunksize(globalMap[0][0].getChunckWidht());
	}
	
	public void DrawEntity(Graphics g) {
		for(Entity e : this.allEntity) {
			if(!e.isInInventory()) {
				e.Draw(g);
			}
		}
	}
	public void UpdateEntity() {
		for(Entity e : this.allEntity) {
			if(e.isErase()) {
				this.deleteEntity.add(e);
			}
			else{
				e.updateEntity(this);
			}
		}
		if(!this.deleteEntity.isEmpty()) {
			for (Entity d2: this.deleteEntity) {
				if(!d2.isInInventory())
					this.allEntity.remove(d2);
			}
		}
		this.allEntity.trimToSize();
		this.deleteEntity.clear();
	}
	public Entity getEntityFromIndex(int x, int y) {
		for(Entity e : this.allEntity) {
			if(e.getX()/this.tileSize == x && e.getY()/this.tileSize == y) {
				return e;
			}
		}
		return null;
	}
	
	
	
	public void DrawWorldMap(Graphics g, int renderx, int rendery, int posX, int posY) {
		for(int y = -rendery ; y < rendery; y++) {
			for (int x = -renderx; x < renderx; x++) {
				if(posX+x >= 0  && posY+y >= 0 && posX+x < this.mapWidth && posY+y < this.mapHeight ) {
					if(globalMap[posX+x][posY+y] != null) {
						globalMap[posX+x][posY+y].DrawChunk(g);
						globalMapl3[posX+x][posY+y].DrawChunk(g);
						globalMapl2[posX+x][posY+y].DrawChunk(g);
						
					}
				}
			}
		}
		this.DrawEntity(g);
	}
	public void UpdateWorlMap(int renderx, int rendery, int posX, int posY, AssetManager asset) {
		for(int y = -rendery ; y < rendery; y++) {
			for (int x = -renderx; x < renderx; x++) {
				if(posX+x > 0  && posY+y > 0 && posX+x < this.mapWidth && posY+y < this.mapHeight ) {
					if(globalMap[posX+x][posY+y] != null) {
						globalMap[posX+x][posY+y].UpdateChunk();
						globalMapl3[posX+x][posY+y].UpdateChunk(this, posX+x, posY+y, asset);
						globalMapl2[posX+x][posY+y].UpdateChunk(this, posX+x, posY+y, asset);
					}
				}
			}
		}
		
	}
	public Chunk getChunkFromIndex(int x, int y, Chunk[][] globalMap) {
		return globalMap[x][y];
	}
	
	public Tile getTileFromChunk(int x, int y) {
		return globalMapl2[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].getTileFromIndex(x, y);
	}
	public void setTileFromIndex(int x, int y, Tile tile) {
		globalMapl2[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].SetTileFromIndex(x, y, tile);
	}
	public Tile getTileFromChunkl1(int x, int y) {
		return globalMap[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].getTileFromIndex(x, y);
	}
	public void setTileFromIndexl1(int x, int y, Tile tile) {
		globalMap[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].SetTileFromIndex(x, y, tile);
	}
	public Tile getTileFromChunkl2(int x, int y) {
		return globalMapl3[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].getTileFromIndex(x, y);
	}
	public void setTileFromIndexl2(int x, int y, Tile tile) {
		globalMapl3[Math.round(x/this.chunksize)][Math.round(y/this.chunksize)].SetTileFromIndex(x, y, tile);
	}




	public int getTileSize() {
		return tileSize;
	}
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	public int getMapWidht() {
		return this.mapWidth;
	}
	public int getMapHeight() {
		return this.mapHeight;
	}
	public long getSeed() {
		return this.seed;
	}



	public int getChunksize() {
		return chunksize;
	}



	public void setChunksize(int chunksize) {
		this.chunksize = chunksize;
	}
	public ArrayList<Entity> getDeleteEntity() {
		return deleteEntity;
	}
	public void setDeleteEntity(ArrayList<Entity> deleteEntity) {
		this.deleteEntity = deleteEntity;
	}


}
