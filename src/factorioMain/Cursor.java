package factorioMain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cursor {
	
	float x,y;
	private Image image;
	private int angle = 0;
	private boolean placing = false;
	private boolean breaking = false;
	private boolean breakingore = false;
	private Tile tile;
	int tileSize;

	public Cursor(Image image) {
		this.image = image;
	}

	public void Draw(Graphics g) {
		if(this.tile != null) {
			if(this.tile.getAnimation() == null) {
				this.tile.getImage().setRotation(this.angle);
				g.drawImage(this.tile.getImage(),Math.round(this.x/this.tileSize)*this.tileSize, Math.round(this.y/this.tileSize)*this.tileSize);
			}
			else {
				//System.out.println(this.tile.getAnimation().getFrameCount());
				for (int i = 0; i < this.tile.getAnimation().getFrameCount(); i++) {
					this.tile.getAnimation().getImage(i).setRotation(this.angle);
				}
				g.drawAnimation(this.tile.getAnimation(), Math.round(this.x/this.tileSize)*this.tileSize, Math.round(this.y/this.tileSize)*this.tileSize);
			}
		}
		g.drawImage(this.image, Math.round(this.x/this.tileSize)*this.tileSize, Math.round(this.y/this.tileSize)*this.tileSize);
	}
	
	public int getTileXIndex() {
		return Math.round(this.x/this.tileSize);
	}
	public int getTileYIndex() {
		return Math.round(this.y/this.tileSize);
	}

	public boolean isPlacing() {
		return placing;
	}

	public void setPlacing(boolean placing) {
		this.placing = placing;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		if(this.angle >= 360) {
			this.angle = 0;
		}
		else
			this.angle = angle;
	}

	public boolean isBreaking() {
		return breaking;
	}

	public void setBreaking(boolean breaking) {
		this.breaking = breaking;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public boolean isBreakingore() {
		return breakingore;
	}

	public void setBreakingore(boolean breakingore) {
		this.breakingore = breakingore;
	}

}
