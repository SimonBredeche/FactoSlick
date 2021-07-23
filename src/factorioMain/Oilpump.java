package factorioMain;

import org.newdawn.slick.Animation;

public class Oilpump extends Tile{
	public Oilpump(Animation animation,String name, boolean rotable) {
		super(animation.getImage(0), name, rotable);
		this.animation = animation;
		// TODO Auto-generated constructor stub
	}
	private int oil = 0;
	private static final int maxoil = 20;

	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		tick++;
		if(this.tick > 20) {
			if(world.getTileFromChunkl2(this.getX(), this.getY()).getName() == "oil") {
				if(this.oil < maxoil){
					oil ++;
				}
			}
			this.tick = 0;
		}
		
	}
	public int getoil() {
		return this.oil;
	}
	
	@Override
	public String toString() {
		String temp = "Buffed oil : " + this.oil;
		return temp;
		
	}
	


}
