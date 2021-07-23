package factorioMain;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

public class MainMenu implements ComponentListener{
	private Image imageBack, Button;
    private MouseOverArea NewGame;
	private MouseOverArea LoadGame;
	private MouseOverArea Exit;
	private ArrayList<MouseOverArea> SlotList;
	private ArrayList<Save> SaveList;
	private int x = 0, y = 0;
	private static final int width = 1920;
	private static final int height = 1080;
	private static final int Y_PADDING = 15; 
	private static final int X_PADDING = 30;
	private boolean open = true;
	private boolean closeGame = false;
	private boolean showSaveList = false;
	private Save selectedSave;
	
	public void loadSaveSlot(GameContainer container) {
		int YPADDING2 = this.Button.getHeight()+Y_PADDING;
		for(int i = 0; i < 4; i++) {
			this.SlotList.add(new MouseOverArea(container, this.Button, width-Button.getWidth(),0+Button.getHeight()+YPADDING2 ,this));
			this.SaveList.add(new Save("Slot"+Integer.toString(i+1),container));
			YPADDING2 += this.Button.getHeight()+Y_PADDING;
		}
	}
	public MainMenu(AssetManager asset, GameContainer container) {
		this.SlotList = new ArrayList<MouseOverArea>();
		this.SaveList = new ArrayList<Save>();
		this.imageBack = asset.menubackground;
		this.Button = asset.buttonImg;
	    NewGame = new MouseOverArea(container,this.Button, (width/2-this.Button.getWidth()/2),(height/2-this.Button.getHeight()/2) ,this);
	    LoadGame = new MouseOverArea(container,this.Button, width-Button.getWidth(),0+Button.getHeight() ,this);
	    Exit = new MouseOverArea(container,this.Button, 0,height-Button.getHeight() ,this);
	    this.loadSaveSlot(container);
		
	}
	public void DrawMenu(Graphics g, GameContainer container) {
		g.setColor(Color.black);
		g.drawImage(imageBack.getScaledCopy(width, height),this.x, this.y);
		NewGame.render(container, g);
		g.drawString("NEW GAME", NewGame.getX()+X_PADDING, NewGame.getY()+Y_PADDING);
		LoadGame.render(container, g);
		g.drawString("LOAD GAME", LoadGame.getX()+X_PADDING,LoadGame.getY()+Y_PADDING);
		Exit.render(container, g);
		g.drawString("EXIT GAME", Exit.getX()+X_PADDING,Exit.getY()+Y_PADDING);
		if(this.showSaveList) {
			for(int i = 0; i < this.SaveList.size();i++) {
				this.SlotList.get(i).render(container, g);
				g.drawString("Slot"+(i+1), SlotList.get(i).getX()+X_PADDING,SlotList.get(i).getY()+Y_PADDING);
			}
		}
		g.setColor(Color.white);
	}
	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		if(this.showSaveList) {
			for(int i = 0; i < this.SlotList.size();i++) {
				if(source == this.SlotList.get(i)) {
					this.selectedSave = this.SaveList.get(i);
					this.showSaveList = false;
					this.open = false;
				}
			}
		}
		if(source == this.NewGame) {
			this.setOpen(false);
		}
		else if(source == this.Exit) {
			this.setCloseGame(true);
		}
		else if(source == this.LoadGame) {
			this.showSaveList = !this.showSaveList;
		}

	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isCloseGame() {
		return closeGame;
	}
	public void setCloseGame(boolean closeGame) {
		this.closeGame = closeGame;
	}
	public Save getSelectedSave() {
		return selectedSave;
	}
	public void setSelectedSave(Save selectedSave) {
		this.selectedSave = selectedSave;
	}

}
