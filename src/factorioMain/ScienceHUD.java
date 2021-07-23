package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

public class ScienceHUD implements ComponentListener{
	private ArrayList<MouseOverArea> ScienceClick;
	private ArrayList<Science> allScience;
	private Image Backimage;
	private Image unknowImage;
	private ItemInventory it;
	private int slotx, sloty;
	private final int BASEX, BASEY; 
	private static final int PADDING = 10;
	private boolean open = false;
	private GameContainer container;
	private Inventory itt;
	private Craft craft;
	public ScienceHUD(AssetManager asset, ItemInventory it, GameContainer container,Inventory itt) {
		this.Backimage = asset.Inventory;
		this.unknowImage = asset.unknowScience;
		this.ScienceClick = new ArrayList<MouseOverArea>();
		this.allScience = new ArrayList<Science>();
		this.slotx = this.Backimage.getWidth()/2;
		this.sloty = this.Backimage.getHeight()/2;
		this.BASEX = this.Backimage.getWidth()/2;
		this.BASEY = this.Backimage.getHeight()/2;
		this.it = it;
		this.itt = itt;
		this.container = container;
		
	}
	
	public void addScience(Science science, GameContainer container) {
		this.ScienceClick.add(new MouseOverArea(container,science.getImage(),this.slotx,this.sloty,this));
		this.allScience.add(science);
		if(this.slotx + science.getImage().getWidth() < this.BASEX*2) {
			this.slotx += science.getImage().getWidth()+PADDING;
		}
		else {
			this.slotx = this.BASEX;
			this.sloty += science.getImage().getHeight()+PADDING;
		}
	}
	public void Draw(Graphics g) {
		g.drawImage(Backimage, BASEX, BASEY);
		for(int i = 0; i < this.ScienceClick.size(); i++) {
			if(this.allScience.get(i).isLocked()) {
				g.drawImage(unknowImage, this.ScienceClick.get(i).getX(), this.ScienceClick.get(i).getY());
			}
			else {
				this.ScienceClick.get(i).render(this.container, g);
			}
		}
		if(this.craft != null) {
			this.craft.DrawCraft(g);
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		for(int i = 0; i < this.ScienceClick.size();i++) {
			if(this.ScienceClick.get(i).isMouseOver()) {
				if(this.allScience.get(i).getCraft() != null) {
					this.craft = this.allScience.get(i).getCraft();
				}
			}
			if(source == this.ScienceClick.get(i) && !this.allScience.get(i).isClicked() && !this.allScience.get(i).isLocked()) {
				if(this.allScience.get(i).getCraft().ItemValid(this.it)) {
					this.allScience.get(i).getCraft().removedItems(it);
					this.allScience.get(i).UnlockScience(itt, container);
					this.allScience.get(i).setClicked(true);
				}

			}
		}
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Science> getAllScience(){
		return this.allScience;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
