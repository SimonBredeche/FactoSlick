package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Science {
	private Craft craft;
	private ArrayList<Tile> unlockedContent;
	private Image image;
	private boolean clicked = false;
	private boolean locked = false;
	private String name;
	private ArrayList<Science> unlockedScience;
	public Science(Image image, String name) {
		this.unlockedContent = new ArrayList<Tile>();
		this.unlockedScience = new ArrayList<Science>();
		this.setImage(image);
		this.setName(name);
	}
	public void addUnlockedScience(Science s) {
		s.setLocked(true);
		this.unlockedScience.add(s);
	}
	public void UnlockScience(Inventory inventory, GameContainer container) {
		for(Tile t : unlockedContent) {
			inventory.addTiles(t, container);
			for(Science s : this.unlockedScience) {
				s.setLocked(false);
			}
		}
	}
	public void addContent(Tile tile) {
		this.unlockedContent.add(tile);
	}
	public Craft getCraft() {
		return craft;
	}
	public void setCraft(Craft craft) {
		this.craft = craft;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Science> getUnlockedScience() {
		return unlockedScience;
	}
	public void setUnlockedScience(ArrayList<Science> unlockedScience) {
		this.unlockedScience = unlockedScience;
	}
	public boolean isClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
