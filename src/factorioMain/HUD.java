package factorioMain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class HUD {
	private String info = "No tile selected";
	private Image image;
	private int x = 50, y = 50;
	public HUD(AssetManager asset) {
		this.image = asset.infobox;
	}
	public void Draw(Graphics g) {
		g.resetTransform();
		g.setColor(Color.magenta);
		g.drawImage(this.image, x, y);
		g.drawString(this.info, x, y);
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
