package factorioMain;

import org.newdawn.slick.Graphics;


public class Chunk {
	private int gridx, gridy;
	private final int Widht = 12;
	private final int Height = 12;
	private Tile[][] area;
	private int tileSize = 32;
	
	public void reset() {
		area = new Tile[this.Widht][this.Height];
	}
	
	public Tile[][] getArea(){
		return this.area;
	}
	public Chunk(AssetManager asset, int gridx, int gridy, Noise2D noise) {
		area = new Tile[this.Widht][this.Height];
		this.gridx = gridx;
		this.gridy = gridy;
		this.generateChunk(asset, noise);
		this.setTileSize(area[0][0].getWidth());
	}
	public Chunk(AssetManager asset, int gridx, int gridy, Noise2D noise, Chunk chunk) {
		area = new Tile[this.Widht][this.Height];
		this.gridx = gridx;
		this.gridy = gridy;
		this.generateVoidChunk(asset,noise,chunk);
	}
	public Chunk(AssetManager asset, int gridx, int gridy, Noise2D[] noise,Chunk chunk) {
		area = new Tile[this.Widht][this.Height];
		this.gridx = gridx;
		this.gridy = gridy;
		this.generateOreChunk(asset, noise,chunk);
	}
	public void DrawChunk(Graphics g) {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				if(area[x][y].getName() != "void") {
					if(area[x][y].getAnimation() == null) {
						area[x][y].getImage().setRotation(area[x][y].getAngle());
						g.drawImage(area[x][y].getImage(),(x*area[x][y].getWidth())+this.gridx*area[x][y].getWidth()*this.Widht, (y*area[x][y].getHeight())+this.gridy*area[x][y].getHeight()*this.Height);
					}
					else {
						for (int i = 0; i < area[x][y].getAnimation().getFrameCount(); i++) {
							area[x][y].getAnimation().getImage(i).setRotation(area[x][y].getAngle());
						}
						//area[x][y].getAnimation().
						g.drawAnimation(area[x][y].getAnimation(), (x*area[x][y].getWidth())+this.gridx*area[x][y].getWidth()*this.Widht, (y*area[x][y].getHeight())+this.gridy*area[x][y].getHeight()*this.Height);
					}
				}
			}
		}
	}
	public void UpdateChunk() {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				if(area[x][y].getName() != "void") {
					area[x][y].update();
				}
			}
		}
		
	}
	public void UpdateChunk(WorldGeneration world,int posX,int posY, AssetManager asset) {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				if(area[x][y].getName() != "void") {
					area[x][y].update(world, posX, posY, asset);
				}
			}
		}
		
	}
	public void generateVoidChunk(AssetManager asset, Noise2D noise, Chunk chunk) {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				double value = noise.eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1 );
				this.TypeAssimTree(area, x, y, asset,value, chunk);
				area[x][y].setX(this.gridx*this.Widht+x);
				area[x][y].setY(this.gridy*this.Height+y);
			}
		}
	}
	public void generateChunk(AssetManager asset, Noise2D noise) {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				double value = noise.eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1 );
				this.TypeAssim(area, x, y, asset,value);
				area[x][y].setX(this.gridx*this.Widht+x);
				area[x][y].setY(this.gridy*this.Height+y);
				
			}
		}
	}
	public void generateOreChunk(AssetManager asset, Noise2D[] noise,Chunk chunk) {
		for (int y = 0; y < area.length;y++) {
			for(int x = 0; x < area[y].length; x++) {
				double ironvalue = noise[0].eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1 );
				double coppervalue = noise[1].eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1 );
				double coalvalue = noise[2].eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1);
				double oilvalue = noise[3].eval((x+(gridx*this.Widht)) * 0.1 , (y+(gridy*this.Height)) * 0.1);
				this.TypeAssimIron(area, x, y, asset,ironvalue, chunk);
				this.TypeAssimCopper(area, x, y, asset,coppervalue, chunk);
				this.TypeAssimCoal(area, x, y, asset,coalvalue, chunk);
				this.TypeAssimOil(area, x, y, asset,oilvalue, chunk);
				area[x][y].setX(this.gridx*this.Widht+x);
				area[x][y].setY(this.gridy*this.Height+y);
				
			}
		}
		
	}
	public void TypeAssim(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue) {
		if(noisevalue <= 0.2) {
			zone[x][y] = asset.Grass;
			
		}
		else if (noisevalue <= 0.5){
			zone[x][y] = asset.Dirt;
		}
		else {
			zone[x][y] = asset.Water;
		}
		
	}
	public void TypeAssimIron(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue, Chunk chunk) {
		if (noisevalue >= 0.6 && chunk.area[x][y].getName() != "Water"){
			zone[x][y] = new Iron(asset.iron, "Iron", asset.Iron);
		}
		else if(zone[x][y] == null){
			zone[x][y] = asset.voidt;
		}
		
	}
	public void TypeAssimOil(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue, Chunk chunk) {
		if (noisevalue >= 0.7 && chunk.area[x][y].getName() != "Water"){
			int rng = (int)(Math.random()*10);
			if(rng < 2)
				zone[x][y] = new Oil(asset.oilImg, "oil");
		}
		else if(zone[x][y] == null){
			zone[x][y] = asset.voidt;
		}
		
	}
	public void TypeAssimCopper(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue, Chunk chunk) {
		if (noisevalue >= 0.6 && chunk.area[x][y].getName() != "Water"){
			zone[x][y] = new Iron(asset.copper, "Copper", asset.Copper);
		}
		else if(zone[x][y] == null){
			zone[x][y] = asset.voidt;
		}
		
	}
	public void TypeAssimCoal(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue, Chunk chunk) {
		if (noisevalue >= 0.6 && chunk.area[x][y].getName() != "Water"){
			zone[x][y] = new Iron(asset.coal, "Coal", asset.Coal);
		}
		else if(zone[x][y] == null){
			zone[x][y] = asset.voidt;
		}
		
	}
	public void TypeAssimTree(Tile[][] zone, int  x ,int y, AssetManager asset, double noisevalue, Chunk chunk) {
		if (noisevalue >= 0.5 && chunk.area[x][y].getName() != "Water"){
			Tile temp = asset.Tree;
			temp.setEntity(asset.Wood);
			zone[x][y] = temp;
		}
		else if(zone[x][y] == null){
			zone[x][y] = asset.voidt;
		}
		
	}
	public Tile getTileFromIndex(int x, int y) {
		int tx = x-this.gridx*this.Widht;
		int ty = y-this.gridy*this.Height;
		return this.area[tx][ty];

	}
	
	public void SetTileFromIndex(int x, int y, Tile tile) {
		int tx = x-this.gridx*this.Widht;
		int ty = y-this.gridy*this.Height;
		this.area[tx][ty] = tile;
	}
	public int getChunckWidht() {
		return this.Widht;
	}
	public int getChunckHeight() {
		return this.Height;
	}
	public int getTileSize() {
		return tileSize;
	}
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	

}
