import processing.core.PApplet;
import processing.core.PImage;

public class PlayerSprite {

	private PApplet p;
	private PImage sheet;
	private PImage sprite;
	public int count;
	private int anim;
	public int dir;
	private final int x = 350;
	private final int y = 200;
	public boolean updated;
	
	public PlayerSprite(PApplet app) {
		p = app;
		count = 0;
		sheet = p.loadImage("gamefiles/game/player/sprite-sheet.png");
		sheet.resize(400, 400);
		anim = 0;
		dir = 0;
		updated = false;
	}

	public void updateA(char k) {
		
		anim = (count/10)%4;
	}
	
	public void updateD(char k) {
		switch (k) {
		case 'w':
			dir = 3;
			break;
		case 'a':
			dir = 1;
			break;
		case 's':
			dir = 0;
			break;
		case 'd':
			dir = 2;
			break;
		default:
			break;
		}
	}
	
	public void updateA() {
		anim = 0;
	}
	
	public void sprite() {
		sprite = sheet.get(100*anim, 100*dir, 100, 100);
		p.image(sprite, x, y);
	}

	
}
