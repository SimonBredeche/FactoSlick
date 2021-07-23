package factorioMain;

import org.newdawn.slick.Animation;


public class Miner extends Tile{
	public Miner( Animation animation,String name, boolean rotable) {
		super(animation.getImage(0), name, rotable);
		this.animation = animation;
	}
	public Miner(Miner miner) {
		super(miner);
		this.animation = miner.getAnimation();
		System.out.println("Constructor called");
	}
	@Override
	public String toString() {
		return this.name + " Orientation : " + this.getDirection();
		
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		String currentOre = "";
		switch((world.getTileFromChunkl2(this.getX(), this.getY()).getName())) {
			case "Coal": currentOre = "Coal"; break;
			case "Iron": currentOre = "Iron";break;
			case "Copper": currentOre = "Copper";break;
			default : this.setAnimation(null); break;
		}
		if(this.tick > 200) { //update
			boolean damage = false;
			int coefx = 0;
			int coefy = 0;
			switch(this.getDirection()) {
			case "north": coefx = 0; coefy = -1;break;
			case "south": coefx = 0; coefy = 1;break;
			case "east": coefx = 1; coefy = 0;break;
			case "west": coefx = -1; coefy = 0;break;
			} 	
			switch(currentOre) {
				case "Coal":  
				if(world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).getCapacity() < 1) {
						world.AddEntity(new Entity(asset.coalentity,(this.getX()+coefx)*world.getTileSize(), (this.getY()+coefy)*world.getTileSize(),"Coal"));
						damage = true;
					}
				}
				break;
				case "Iron":  
					if(world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy).getTag() == "Conveyor") {
						if(((Conveyor)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).getCapacity() < 1) {
							world.AddEntity(new Entity(asset.ironentity,(this.getX()+coefx)*world.getTileSize(), (this.getY()+coefy)*world.getTileSize(),"Iron"));
							damage = true;
						}
					}
				break;
				case "Copper":  
					if(world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy).getTag() == "Conveyor") {
						if(((Conveyor)world.getTileFromChunk(this.getX()+coefx, this.getY()+coefy)).getCapacity() < 1) {
							world.AddEntity(new Entity(asset.copperentity,(this.getX()+coefx)*world.getTileSize(), (this.getY()+coefy)*world.getTileSize(),"Copper"));
							damage = true;
						}
					}
				break;
			}

				
			
			if(damage) {
				((Iron) world.getTileFromChunkl2(this.getX(), this.getY())).setDurability(((Iron) world.getTileFromChunkl2(this.getX(), this.getY())).getDurability()-1);
				this.setAnimation(asset.Mineranim);
				
			}
			this.tick = 0;
		}
	}
	

}
