package factorioMain;

public class Camera {
	private float xCam = 300, yCam = 300;
	private float prevXcam = 300, prevYcam = 300;
	private int speed = 8;
	boolean north, south , west, east;
	public float getxCam() {
		return xCam;
	}

	public void setxCam(float xCam) {
		this.xCam = xCam;
	}

	public float getyCam() {
		return yCam;
	}

	public void setyCam(float yCam) {
		this.yCam = yCam;
	}

	public float getPrevXcam() {
		return prevXcam;
	}

	public void setPrevXcam(float prevXcam) {
		this.prevXcam = prevXcam;
	}

	public float getPrevYcam() {
		return prevYcam;
	}

	public void setPrevYcam(float prevYcam) {
		this.prevYcam = prevYcam;
	}
	public void updateCam(WorldGeneration world) {
		if(north) {
			if((int)((this.yCam/world.getTileSize())/15) > 0)
				this.yCam -= speed;
		}
		else if(south) {
			if((int)((this.yCam/world.getTileSize())/15) < world.getMapHeight())
			this.yCam += speed;
		}
		if(west) {
			if((int)((this.xCam/world.getTileSize())/15) > 0)
				this.xCam -= speed;
		}
		else if(east ) {
			if((int)((this.xCam/world.getTileSize())/15) < world.getMapWidht())
				this.xCam += speed;
		}
	}
}
