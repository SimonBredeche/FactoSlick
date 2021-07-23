package factorioMain;

import org.newdawn.slick.Animation;

public class Gearmaker extends Furnace{

	public Gearmaker(Animation animation, String name) {
		super(animation, name);
		this.rotable = false;
	}
	
	@Override
	public boolean IsValidEntity(Entity e) {
		switch(e.getName()) {
			case "Iron plate": return true;
			case "Copper plate": return true;
			default: return false;
		}
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 10) { //update 
			if(this.entity != null) {
				this.animation = asset.gearmakeranim;
				if(this.smelt > 50) {
					Entity temp;
					switch(this.entity.getName()) {
					case "Iron plate":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.irongear,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Iron gear");
						temp.setInInventory(true);
						world.AddEntity(temp);
						this.smelt = 0;
						this.setReady(true);
						break;
					case "Copper plate":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.coppergear,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Copper gear");
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



}
