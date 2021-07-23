package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Conveyor extends Tile{
	private int conveyorSpeed;
	private ArrayList<Entity> currentEnrity;
	private int capacity = 0;
	public Conveyor(Image image, String name, boolean rotable, int conveyorSpeed) {
		super(image, name, rotable);
		this.setTag("Conveyor");
		this.currentEnrity = new ArrayList<Entity>();
		this.conveyorSpeed = conveyorSpeed;
		this.hardeness = 10;
		// TODO Auto-generated constructor stub
	}


	public int getConveyorSpeed() {
		return conveyorSpeed;
	}
	public void setConveyorSpeed(int conveyorSpeed) {
		this.conveyorSpeed = conveyorSpeed;
	}

	@Override
	public String toString() {
		return this.name + " Capacity: " + this.capacity;
		
	}
	@Override
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {
		this.tick ++;
		if(world.getEntityFromIndex(this.getX(), this.getY()) != null) {
			this.setCapacity(1);
		}
		else {
			this.setCapacity(0);
		}
		if(this.tick > 10) { //update 


			//System.out.println(this.currentEnrity.size());
			this.tick = 0;
		}
	}

	public ArrayList<Entity> getCurrentEnrity() {
		return currentEnrity;
	}

	public void setCurrentEnrity(ArrayList<Entity> currentEnrity) {
		this.currentEnrity = currentEnrity;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
