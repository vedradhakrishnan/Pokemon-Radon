import processing.core.PApplet;
import processing.core.PImage;

public class Title {

	private PApplet p;
	private PImage cover;
	public int count;
	
	public Title(PApplet app) {
		p = app;
		count = 0;
		cover = p.loadImage("gamefiles/title/radon-cover.png");
		cover.resize(800, 700);
	}

	public int opacity(int f) {
		if(count < 255) {
			return 255-1*count;
		} else {
			return 0;
		}
	}
	
	public void disp() {
		p.image(cover, 0, 0);
		p.fill(0, 0, 0, opacity(count));
		p.rect(0, 0, p.width, p.height);
	}
	
}
