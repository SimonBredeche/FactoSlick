package factorioMain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ChargingBar {
	private boolean ready = false;
	private boolean update = false;
	private int tick = 0;
	private Image image;
	private static final int BARWIDHT = 300;
	private static final int BARHEIGHT = 25;
	private static final int POSX =  30;
	private static final int POSY =  1000;
	private int maxTick = 100;
    private static final Color XP_COLOR = new Color(0, 255, 0);
	public ChargingBar(Image image) {
		this.image = image;
	}
	public void resetbar() {
		this.tick = 0;
		this.ready = false;
	}
	
	public void Draw(Graphics g) {
		if(this.update) {
			g.resetTransform();
        	g.setColor(XP_COLOR);
			g.fillRect(POSX, POSY, ((float)tick/maxTick) * BARWIDHT, BARHEIGHT);
			g.drawImage(this.image,POSX,POSY);
		}
	}
	
	public void update() {
		if(this.update) {
			tick++;
			if(this.tick > maxTick) {
				this.tick = 0;
				this.ready = true;
			}
		}
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
		
	}
	public int getMaxTick() {
		return maxTick;
	}
	public void setMaxTick(int maxTick) {
		this.maxTick = maxTick;
	}

}
