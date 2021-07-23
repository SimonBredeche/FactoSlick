package factorioMain;

import org.newdawn.slick.Animation;

public class HighAssemblyMachine extends AssemblyMachine{
	private boolean item3;
	private String itemthree;
	private Entity entity3;
	public HighAssemblyMachine(Animation animation, String name, Entity produced, Entity item1, Entity item2,Entity item3) {
		super(animation, name, produced, item1, item2);
		this.itemthree = item3.getName();
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
		else if(e.getName() == this.itemthree && !this.item3) {
			this.item3 = true;
			return true;
		}
		else {
			return false;
		}

	}
	@Override
	public void setReady(boolean full) {
		if(this.item1 && this.item2 && this.item3) {
			this.ready = true;
		}
		else {
			this.ready = false;
		}
	}
	@Override
	public void setFull(boolean full) {
		if(this.item1 && this.item2 && this.item3) {
			this.full = true;
		}
		else {
			this.full = false;
		}
	}
	
	
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(this.tick > 20) { //update 
			if(this.entity != null && this.entity2 != null && this.entity3 != null && this.item1 && this.item2 && this.item3 &&
					world.getEntityFromIndex(this.getX(), this.getY()) == null) {
				this.animation = this.animation2;
				if(this.smelt > 100) {
					Entity temp;
					this.entity.setInInventory(false);
					this.entity2.setInInventory(false);
					this.entity3.setInInventory(false);
					this.entity = null;
					this.entity2 = null;
					this.entity3 = null;
					temp = new Entity(this.itemimage,this.getX()*world.getTileSize(),this.getY()*world.getTileSize(),this.itemname);
					temp.setInInventory(true);
					world.AddEntity(temp);
					this.smelt = 0;
					this.ready = true;
					this.item1 = false;
					this.item2 = false;
					this.item3 = false;
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
			else if(this.entity3 == null) {
				this.setFull(true);
				this.entity3 = entity;
				this.entity3.setInInventory(true);
			}
			entity.setErase(true);
		}
	}
	@Override
	public String toString() {
		String entity1str = "null";
		String entity2str = "null";
		String entity3str = "null";
		if(this.entity != null) {
			entity1str = this.entity.getName();
		}
		if(this.entity2 != null) {
			entity2str = this.entity2.getName();
		}
		if(this.entity3 != null) {
			entity3str = this.entity3.getName();
		}
		return this.name + "\n" + " Entity1: " + entity1str + "\n" + " Entity2: " + entity2str + "\n" + " Entity3: " + entity3str + "\n" + "Required items : " + itemone +" & "+ itemtwo + " & " + itemthree;

		
	}

	



}
