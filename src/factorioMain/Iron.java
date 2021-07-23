package factorioMain;

import org.newdawn.slick.Image;

public class Iron extends Tile{
	private int Durability = 0;
	public Iron(Image image, String name) {
		super(image, name);
		this.setDurability((int) (Math.random()*(10000-5000)+1)+5000);
		this.hardeness = 100;
		this.setTag("ore");
	}
	public Iron(Image image, String name, Entity entity) {
		super(image, name);
		this.setDurability((int) (Math.random()*(10000-5000)+1)+5000);
		this.hardeness = 100;
		this.setTag("ore");
		this.entity = entity;
	}
	public int getDurability() {
		return Durability;
	}
	public void setDurability(int durability) {
		Durability = durability;
	}
	@Override
	public String toString() {
		return this.name + " Durability : " + this.Durability;
		
	}
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		if(this.Durability <= 0) {
			world.setTileFromIndexl2(this.getX(), this.getY(), asset.voidt);
		}

	}



}
