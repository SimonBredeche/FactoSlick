package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

public class Inventory implements ComponentListener{
	private Image image;
	private boolean isOpen;
	private ArrayList<MouseOverArea> clickTiles;
	private ArrayList<Tile> storedTiles;
	private int slotx, sloty;
	private final int BASEX, BASEY; 
	private Craft currentCraft;
	private static final int PADDING = 5;
	private Cursor cursor;
	public Inventory(AssetManager asset) 
	{
		this.clickTiles = new ArrayList<MouseOverArea>();
		this.storedTiles = new ArrayList<Tile>();
		this.image = asset.Inventory;
		this.slotx = this.image.getWidth()/2;
		this.sloty = this.image.getHeight()/2;
		this.BASEX = this.image.getWidth()/2;
		this.BASEY = this.image.getHeight()/2;
		this.cursor = asset.cursor;
		// TODO Auto-generated constructor stub
	}
	public void addTiles(Tile tile, GameContainer container) {
		storedTiles.add(tile);
		clickTiles.add(new MouseOverArea(container,tile.getImage(), this.slotx+PADDING,this.sloty+PADDING ,this));
		if(this.slotx + tile.getImage().getWidth() < this.BASEX*2) {
			this.slotx += tile.getImage().getWidth()+PADDING;
		}
		else {
			this.slotx = this.BASEX;
			this.sloty += tile.getHeight()+PADDING;
		}
		
		
	}
	public void Draw(Graphics g, GameContainer container) {
		g.resetTransform();
		g.drawImage(this.image, this.BASEX, this.BASEY);
		for (MouseOverArea i : clickTiles) {
			i.render(container, g);
		}
		if(this.currentCraft != null) {
			currentCraft.DrawCraft(g);
		}
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.clickTiles.size(); i++) {
			if(this.clickTiles.get(i).isMouseOver()) {
				if(this.storedTiles.get(i).getCraft() != null) {
					this.currentCraft = this.storedTiles.get(i).getCraft();
				}
			}
			if(this.clickTiles.get(i) == source) {
				this.cursor.setTile(this.storedTiles.get(i));
			}
		}
	
		
	}

}
