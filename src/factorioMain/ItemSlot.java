package factorioMain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ItemSlot {
	private Image image;
	private int number = 1;
	private String name;
	
	public Image getImage() {
		return this.image;
	}
	public ItemSlot(Image image, String name) {
		this.image = image;
		this.setName(name);
	}
	public ItemSlot(Entity e, int number) {
		this.image = e.getImage();
		this.setName(e.getName());
		this.number = number;
	}
	public void draw(Graphics g, int x, int y) {
		g.drawImage(this.image,x,y);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
