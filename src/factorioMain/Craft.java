package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Craft {
	private boolean open;
	private Image craftImg;
	private Image SlotImg;
	private ArrayList<ItemSlot> requiredItems;
	public Craft(AssetManager asset) {
		this.craftImg = asset.CraftImg;
		this.SlotImg = asset.slotimage;
		this.setRequiredItems(new ArrayList<ItemSlot>());
		
	}
	
	public boolean ItemValid(ItemInventory inventory) {
		//boolean AllItemFounds = false;
		int size = 0;
		for(ItemSlot r : this.requiredItems) {
			for(ItemSlot i : inventory.getStoredTiles()) {
				if(i.getName() == r.getName() && i.getNumber() >= r.getNumber()) {
					size++;
				}
			}
		}	
		if(size == requiredItems.size()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void removedItems(ItemInventory inventory) {
		inventory.removeItem(this.requiredItems);
	}
	public void AddRequiredItems(ItemSlot item) {
		this.requiredItems.add(item);
	}
	public void DrawCraft(Graphics g) {
		int y = 1080-(craftImg.getHeight());
		g.drawImage(this.craftImg, 0, y-50);
		g.drawString("Needed Materials : ", 0, y-50);
		for(ItemSlot i : this.requiredItems) {
			g.drawImage(SlotImg, 0, y);
			i.draw(g, (SlotImg.getWidth()/2)-(i.getImage().getWidth()/2), y+(SlotImg.getHeight()/2)-(i.getImage().getHeight()/2));
			g.drawString(i.getName() + " : " + Integer.toString(i.getNumber()), 50, y);
			y += SlotImg.getHeight();
		}
	}
	public ArrayList<ItemSlot> getRequiredItems() {
		return requiredItems;
	}
	public void setRequiredItems(ArrayList<ItemSlot> requiredItems) {
		this.requiredItems = requiredItems;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
