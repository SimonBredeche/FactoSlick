package factorioMain;

import org.newdawn.slick.Animation;

public class ItemCollector extends Furnace{

	public ItemCollector(Animation animation, String name) {
		super(animation, name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean IsValidEntity(Entity e) {
		return true;
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 10) { //update 
			if(this.entity != null) {
				this.animation = asset.itemcollectoranim;
				if(this.smelt > 1) {
					asset.getIteminventory().addItem(this.entity);
					world.getDeleteEntity().add(this.entity);
					this.entity = null;
					this.smelt = 0;
					this.full = false;
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
