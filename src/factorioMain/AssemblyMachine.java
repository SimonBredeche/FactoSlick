package factorioMain;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class AssemblyMachine extends Furnace{
	protected Animation animation2;
	protected boolean item1 = false;
	protected boolean item2 = false;
	protected Image itemimage;
	protected String itemname;
	protected String itemone;
	protected String itemtwo;
	protected Entity entity2;
	public AssemblyMachine(Animation animation,Image itemimage ,String itemname,String name, String item1, String item2) {
		super(animation, name);
		this.animation2 = animation;
		this.itemone = item1;
		this.itemtwo = item2;
		this.itemimage = itemimage;
		this.itemname = itemname;
		this.rotable = false;
		// TODO Auto-generated constructor stub
	}
	public AssemblyMachine(Animation animation,String name,Entity produced, Entity item1, Entity item2) {
		super(animation, name);
		this.animation2 = animation;
		this.itemone = item1.getName();
		this.itemtwo = item2.getName();
		this.itemimage = produced.getImage();
		this.itemname = produced.getName();
		this.rotable = false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean IsValidEntity(Entity e) {
		if(e.getName() == this.itemone && !this.item1) {
			this.item1 = true;
			return true; 
		}
		else if(e.getName() == this.itemtwo && !this.item2) {
			this.item2 = true;
			return true;
		}
		else {
			return false;
		}

	}
	
	@Override
	public void setReady(boolean full) {
		if(this.item1 && this.item2) {
			this.ready = true;
		}
		else {
			this.ready = false;
		}
	}
	@Override
	public void setFull(boolean full) {
		if(this.item1 && this.item2) {
			this.full = true;
		}
		else {
			this.full = false;
		}
	}
	
	
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 10) { //update 
			if(this.entity != null && this.entity2 != null &&this.item1 && this.item2 ) {
					this.animation = this.animation2;
					if(this.smelt > 100) {
						this.entity.setInInventory(false);
						this.entity2.setInInventory(false);
						Entity temp;
						this.entity = null;
						this.entity2 = null;
						temp = new Entity(this.itemimage,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),this.itemname);
						temp.setInInventory(true);
						world.AddEntity(temp);
						this.smelt = 0;
						this.ready = true;
						this.item1 = false;
						this.item2 = false;
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
	
	@Override
	public void setEntity(Entity entity) {
		if(entity != null) {
		this.IsValidEntity(entity);
		if(this.entity == null) {
				this.setFull(true);
				this.entity = entity;
				this.entity.setInInventory(true);
		}
		else if(this.entity2 == null) {
				this.setFull(true);
				this.entity2 = entity;
				this.entity2.setInInventory(true);
		}
			entity.setErase(true);
		}
	}
	@Override
	public String toString() {
		String entity1str = "null";
		String entity2str = "null";
		if(this.entity != null) {
			entity1str = this.entity.getName();
		}
		if(this.entity2 != null) {
			entity2str = this.entity2.getName();
		}
		return this.name + "\n" + " Entity1: " + entity1str + "\n" + " Entity2: " + entity2str + "\n" + "Required items : " + itemone +" & "+ itemtwo;

		
	}


}
