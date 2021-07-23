package factorioMain;

import org.newdawn.slick.Animation;


public class Furnace extends Tile{
	protected Entity entity;
	protected boolean full = false;
	protected boolean ready = false;
	protected int smelt = 0;
	
	public boolean IsValidEntity(Entity e) {
		switch(e.getName()) {
			case "Iron": return true; 
			case "Copper": return true; 
			case "Iron plate": return true;
			default: return false;
		}
	}
	public Furnace(Animation animation, String name) {
		super(animation.getImage(0), name);
		this.animation = animation;
		this.setTag("machines");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 10) { //update 
			if(this.entity != null) {
				this.animation = asset.furnaceanim;
				if(this.smelt > 100) {
					Entity temp;
					switch(this.entity.getName()) {
					case "Iron":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.ironplate,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Iron plate");
						temp.setInInventory(true);
						world.AddEntity(temp);
						this.smelt = 0;
						this.setReady(true);
						break;
					case "Copper":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.copperplate,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Copper plate");
						temp.setInInventory(true);
						world.AddEntity(temp);
						this.smelt = 0;
						this.setReady(true);
						break;
					case "Iron plate":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.steel,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Steel");
						temp.setInInventory(true);
						world.AddEntity(temp);
						this.smelt = 0;
						this.setReady(true);
						break;
					}
					
				}
				else {
					this.smelt++;
				}
				
			}
			else {
				this.animation = null;
			}
			this.tick = 0;
		}
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		if(entity != null) {
			this.setFull(true);
			this.entity = entity;
			this.entity.setInInventory(true);

		}
	}
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}
	@Override
	public String toString() {
		if(this.entity != null) {
			return this.name + " Entity: " + this.entity.getName();
		}
		else {
			return this.name + " Entity: null";
		}
		
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	
	



}
