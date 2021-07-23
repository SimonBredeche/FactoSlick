package factorioMain;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Tile implements Cloneable{
	protected static int ID = 0;
	protected Image image;
	protected String name;
	protected int Width;
	protected int Height;
	protected int tick;
	private int x,y;
	protected int angle = 0;
	protected boolean rotable;
	protected Animation animation;
	protected int hardeness = 100;
	private String tag = "tile";
	private Craft craft;
	protected Entity entity;
	public int getID() {
		return ID;
	}
	public Entity getEntity() {
		return this.entity;
	}
	public Tile(Image image, String name, boolean rotable) {
		this.image = image;
		this.name = name;
		this.Width = image.getWidth();
		this.Height = image.getHeight();
		this.rotable = rotable;
		ID++;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Tile(Image image, String name, int hardeness) {
		this.image = image;
		this.name = name;
		this.Width = image.getWidth();
		this.Height = image.getHeight();
		this.hardeness = hardeness;
		ID++;
	}
	public int getHardeness() {
		return this.hardeness;
	}
	public Tile(Image image, String name) {
		this.image = image;
		this.name = name;
		this.Width = image.getWidth();
		this.Height = image.getHeight();
		this.rotable = false;
		ID++;
	}
	public String getDirection() {
		switch(this.angle){
			case 90: return "east";
			case 180: return "south";
			case 270: return "west";
			default : return "north";
		}
	}
	public Tile(Tile tile) {
		this.image = tile.getImage();
		this.name = tile.getName();
		this.Width = tile.getWidth();
		this.Height = tile.getHeight();
		this.rotable = tile.isRotable();
		ID++;
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}


	public Image getImage() {
		return image;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return Width;
	}
	public int getHeight() {
		return Height;
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
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		if(this.angle >= 360) {
			this.angle = 90;
		}
		else {
			this.angle = angle;
		}
		
	}
	public boolean isRotable() {
		return rotable;
	}
	public String toString() {
		return this.name;
	}
	public void update() {}
	public void update(WorldGeneration world, int posX, int posY, AssetManager asset) {};
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Craft getCraft() {
		return craft;
	}
	public void setCraft(Craft craft) {
		this.craft = craft;
	}



}
