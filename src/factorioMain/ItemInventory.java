package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ItemInventory{

	private Image image;
	private Image SlotImage;
	private boolean isOpen;
	private ArrayList<ItemSlot> storedTiles;
	private int slotx, sloty;
	private final int BASEX, BASEY; 
	private static final int PADDING = 7;
	
	public ItemInventory(AssetManager asset) 
	{
		this.storedTiles = new ArrayList<ItemSlot>();
		this.image = asset.Inventory;
		this.SlotImage = asset.slotimage;
		this.slotx = this.image.getWidth()/2;
		this.sloty = this.image.getHeight()/2;
		this.BASEX = this.image.getWidth()/2;
		this.BASEY = this.image.getHeight()/2;

	}
	public boolean isNewSlot(Entity e) {
		boolean found = false;
		for(ItemSlot i : this.storedTiles) {
			if(i.getName() == e.getName()) {
				found = true;
				i.setNumber(i.getNumber()+1);
			}
		}
		if(!found) {
			return true;
		}
		else {
			return false;
		}
	}
	public void removeItem(ArrayList<ItemSlot> items) {
		for (ItemSlot is : items) {
			for(ItemSlot is2 : this.storedTiles) {
				if(is2.getName().equals(is.getName())){
					is2.setNumber(is2.getNumber()-is.getNumber());
				}
			}
		}
	}
	public void addItemSlot(ItemSlot is) {
		this.storedTiles.add(is);
	}
	public void addItem(Entity e) {
		if(e != null) {
			if(this.isNewSlot(e) || this.storedTiles.isEmpty()) {
				storedTiles.add(new ItemSlot(e.getImage(),e.getName()));
			}
		}
	}
	public void Draw(Graphics g, GameContainer container) {
		g.resetTransform();
		g.drawImage(this.image, this.BASEX, this.BASEY);
		this.slotx = this.BASEX;
		this.sloty = this.BASEY;
		for (ItemSlot is : storedTiles) {
			g.drawImage(SlotImage, slotx+PADDING, sloty+PADDING);
			is.draw(g,slotx + (SlotImage.getWidth()/2)-(is.getImage().getWidth()/2) + PADDING ,sloty + (SlotImage.getHeight()/2)-(is.getImage().getHeight()/2) + PADDING);
			if(is.getNumber() <= 999)
				g.drawString(Integer.toString(is.getNumber()), slotx + PADDING, sloty+28 + PADDING);
			else
				g.drawString("+999", slotx + PADDING, sloty+28 + PADDING);
			if(slotx > (this.image.getWidth()*2)-32) {
				sloty += this.SlotImage.getHeight()+PADDING;
				slotx = this.BASEX;
			}
			else {
				slotx += this.SlotImage.getWidth()+PADDING;
			}
		}
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public ArrayList<ItemSlot> getStoredTiles(){
		return this.storedTiles;
	}


}
