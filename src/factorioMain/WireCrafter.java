package factorioMain;

import org.newdawn.slick.Animation;

public class WireCrafter extends Furnace{

	public WireCrafter(Animation animation, String name) {
		super(animation, name);
		this.rotable = false;
	}
	
	@Override
	public boolean IsValidEntity(Entity e) {
		switch(e.getName()) {
			case "Copper plate": return true;
			default: return false;
		}
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 10) { //update 
			if(this.entity != null) {
				this.animation = asset.WireCutter;
				if(this.smelt > 50) {
					Entity temp;
					switch(this.entity.getName()) {
					case "Copper plate":
						this.entity.setInInventory(false);
						world.getDeleteEntity().add(this.entity);
						this.entity = null;
						temp = new Entity(asset.coppercoil,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),"Copper coil");
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
