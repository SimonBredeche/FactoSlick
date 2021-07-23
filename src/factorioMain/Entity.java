package factorioMain;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {
	private Image image;
	private int x,y;
	private String name;
	private Rectangle rect;
	private Conveyor preConveyor;
	private boolean inContact;
	private boolean erase = false;
	private boolean addKapa = true;
	private int tick = 0;
	private boolean inInventory = false;
	public Entity(Image image, int x, int y, String name) {
		this.image = image;
		this.setX(x);
		this.setY(y);
		this.setName(name);
		this.rect = new Rectangle(this.x,this.y,image.getWidth(),image.getHeight());
	}
	public void Draw(Graphics g) {
		g.drawImage(this.image, this.x, this.y);
	}

	public Image getImage() {
		return image;
	}
	public void updateEntity(WorldGeneration world) {
		this.tick++;
		if(world.getTileFromChunk(this.getX()/world.getTileSize(), this.getY()/world.getTileSize()).getTag() == "Conveyor") {
			Conveyor currentConveyor = ((Conveyor) world.getTileFromChunk(this.getX()/world.getTileSize(), this.getY()/world.getTileSize()));
			if(this.preConveyor == null) {
				this.preConveyor = currentConveyor;
			}
			else if(!this.preConveyor.equals(currentConveyor)) {
				this.preConveyor.setCapacity(this.preConveyor.getCapacity()-1);
				this.preConveyor = currentConveyor;
				this.addKapa = true;
			}
			if(this.addKapa) {
				this.preConveyor.setCapacity(this.preConveyor.getCapacity()+1);
				this.addKapa = false;
			}
			switch(currentConveyor.getDirection()) {
			case "south":
				if(world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()+1).getDirection() != "north" 
				&& world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()+1).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()+1)).getCapacity() < 1) {
						this.y += currentConveyor.getConveyorSpeed();
						//this.inContact  = false;
					}
				}
				break;
			case "north": 
				if(world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()-1).getDirection() != "south" 
				&& world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()-1).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(currentConveyor.getX(), currentConveyor.getY()-1)).getCapacity() < 1) { 
						this.y -= currentConveyor.getConveyorSpeed();
						//this.inContact  = false;
					}
				}
			break;
			case "west":
				if(world.getTileFromChunk(currentConveyor.getX()-1, currentConveyor.getY()).getDirection() != "east" 
				&& world.getTileFromChunk(currentConveyor.getX()-1, currentConveyor.getY()).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(currentConveyor.getX()-1, currentConveyor.getY())).getCapacity() < 1) {
						this.x -= currentConveyor.getConveyorSpeed();
						//this.inContact  = false;
					}
				}
				break;
			case "east": 
				if(world.getTileFromChunk(currentConveyor.getX()+1, currentConveyor.getY()).getDirection() != "west" 
				&& world.getTileFromChunk(currentConveyor.getX()+1, currentConveyor.getY()).getTag() == "Conveyor") {
					if(((Conveyor)world.getTileFromChunk(currentConveyor.getX()+1, currentConveyor.getY())).getCapacity() < 1) {
						this.x += currentConveyor.getConveyorSpeed();
						//this.inContact  = false;
					}
				}
				break;
			}
			
		}
		else {
			if(this.tick > 1000) {
				if(!(world.getTileFromChunk((this.getX()+1)/world.getTileSize(), this.getY()/world.getTileSize()).getTag() == "Conveyor"
					|| world.getTileFromChunk((this.getX()-1)/world.getTileSize(), this.getY()/world.getTileSize()).getTag() == "Conveyor"
					|| world.getTileFromChunk((this.getX())/world.getTileSize(), (this.getY()-1)/world.getTileSize()).getTag() == "Conveyor"
					|| world.getTileFromChunk((this.getX())/world.getTileSize(), (this.getY()+1)/world.getTileSize()).getTag() == "Conveyor")
					|| world.getTileFromChunk((this.getX())/world.getTileSize(), (this.getY())/world.getTileSize()).getTag() == "machines") {
					world.getDeleteEntity().add(this);
				}
				this.tick  = 0;
			}
		}

		
		this.rect.setX(this.x);
		this.rect.setY(this.y);
	}
	public Rectangle getRectangle() {
		return this.rect;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean isInContact() {
		return inContact;
	}
	public void setInContact(boolean inContact) {
		this.inContact = inContact;
	}
	public boolean isInInventory() {
		return inInventory;
	}
	public void setInInventory(boolean inInventory) {
		this.inInventory = inInventory;
	}
	public boolean isErase() {
		return erase;
	}
	public void setErase(boolean erase) {
		this.erase = erase;
	}


}
