package factorioMain;

import org.newdawn.slick.Animation;


public class Inserter extends Tile{
	private int range = 1;
	private Animation animation2;
	public Inserter(Animation animation, String name, boolean rotable) {
		super(animation.getImage(0), name, rotable);
		this.animation = animation;
		this.animation2 = animation;
		this.hardeness = 50;
		// TODO Auto-generated constructor stub
	}
	public Inserter(Animation animation, String name, boolean rotable, int range) {
		super(animation.getImage(0), name, rotable);
		this.animation = animation;
		this.animation2 = animation;
		this.hardeness = 50;
		if(range > 0) {
			this.range = range;
		}
	}
	
	@Override
	public String toString() {
		return this.name + " Orientation : " + this.getDirection();
		
	}
	
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		int coefx = 0;
		int coefy = 0;
		switch(this.getDirection()) {
		case "north": coefx = 0; coefy = -range; break;
		case "south": coefx = 0; coefy = range; break;
		case "east": coefx = range; coefy = 0; break;
		case "west": coefx = -range; coefy = 0; break;
		} 
		if(this.tick > 20) { //update 
			
			if(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy) == null && !(world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy).getTag() == "machines")) {
				this.animation = null;
			}
			else {
				if(world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy).getTag() == "machines") { //OUTPUT FROM MACHINES
					if(((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).isReady() && world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy) != null) {
						if(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy).isInInventory()) {
							if(world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy).getTag() == "Conveyor") {
								if(((Conveyor)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).getCapacity() < 1) {
									Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy);
									this.animation = animation2;
									temp.setInInventory(false);
									temp.setY((this.getY()-coefy)*world.getTileSize());
									temp.setX((this.getX()-coefx)*world.getTileSize());
									((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setFull(false);
									((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setReady(false);
								}
								else {
									this.animation = null;
								}

							}
							else if(world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy).getTag() == "machines") { //INPUT INTO MACHINES
								if(!((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).isFull() && world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy) != null) {
									if(((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).IsValidEntity(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy))) {
										Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy); 
										this.animation = animation2;
										((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).setEntity(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy));
										temp.setY((this.getY()-coefy)*world.getTileSize());
										temp.setX((this.getX()-coefx)*world.getTileSize());
										((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setFull(false);
										((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setReady(false);
									}
								}
								else {
									this.animation = null;
								}
							}
							else {
								Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy);
								this.animation = animation2;
								temp.setInInventory(false);
								temp.setY((this.getY()-coefy)*world.getTileSize());
								temp.setX((this.getX()-coefx)*world.getTileSize());
								((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setFull(false);
								((Furnace)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).setReady(false);
							}
						}
						
					}
					else {
						this.animation = null;
					}
				}
				else if(world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy).getTag() == "machines") { //INPUT INTO MACHINES
					if(!((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).isFull() && world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy) != null) {
						if(((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).IsValidEntity(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy))) {
							Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy); 
							this.animation = animation2;
							((Furnace)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).setEntity(world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy));
							temp.setY((this.getY()-coefy)*world.getTileSize());
							temp.setX((this.getX()-coefx)*world.getTileSize());
						}
					}
					else {
						this.animation = null;
					}
				}
				else if(world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).getCapacity() < 1) {
						Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy);
						this.animation = animation2;
						temp.setY((this.getY()-coefy)*world.getTileSize());
						temp.setX((this.getX()-coefx)*world.getTileSize());
						((Conveyor)world.getTileFromChunk(this.getX()-coefx, this.getY()-coefy)).setCapacity(0);
					}
					else {
						this.animation = null;
					}
				}
				else {
					if(world.getEntityFromIndex(this.getX()-coefx, this.getY()-coefy) == null) {
						Entity temp = world.getEntityFromIndex(this.getX()+coefx, this.getY()+coefy);
						this.animation = animation2;
						temp.setY((this.getY()-coefy)*world.getTileSize());
						temp.setX((this.getX()-coefx)*world.getTileSize());
					}
					else {
						this.animation = null;
					}
				}
				
				
			}

			this.tick = 0;
		}
	}



}
