import processing.core.PApplet;
import processing.core.PImage;

public class MapSprite {

	private PApplet p;
	private PImage sheet;
	private PImage[] baseTiles;
	private PImage[] objectTiles;
	private PImage tile;
	
	public MapSprite(PApplet app) {
		p = app;
		sheet = p.loadImage("gamefiles/game/map/base-sprite-sheet.png");
		baseTiles = new PImage[8];
		for (int i = 0; i < baseTiles.length; i++) {
			baseTiles[i] = tile = sheet.get(32*i, 0, 32, 32);
			baseTiles[i].resize(100, 100);
		}
		
		sheet = p.loadImage("gamefiles/game/map/objects-sprite-sheet.png");
		
		objectTiles = new PImage[5];
		objectTiles[0] = sheet.get(0, 0, 300, 300);
		objectTiles[1] = sheet.get(300, 0, 100, 100);
		objectTiles[2] = sheet.get(400, 0, 100, 100);
		objectTiles[3] = sheet.get(500, 0, 200, 200);
		objectTiles[4] = sheet.get(700, 0, 100, 100);


	}

	public void dispBase(int[][] b, int[][] bl, double r, double c) {
		int x = (int)(Math.round(c));
		int y = (int)(Math.round(r));
		p.noStroke();
		for (int i = y-6; i < y+6; i++) {
			for (int j = x-6; j < x+6; j++) {
				
				tile = baseTiles[b[i][j]];
				p.image(tile, (float)(350-100*(c-j)), (float)(220-100*(r-i)));
				
//				switch (b[i][j]) {
//				
//				}
//				case 0:
//					p.fill(66,135,245);
//					break;
//				case 1:
//					p.fill(66,245,135);
//					break;
//				case 2:
//					p.fill(248, 255, 115);
//					break;
//				case 3:
//					p.fill(135,135,135);
//					break;
//				case 4:
//					p.fill(0,156,136);
//					break;
//				case 5:
//					p.fill(200,170,111);
//					break;
//				case 6:
//					p.fill(245,250,255);
//					break;
//				case 7:
//					p.fill(0,0,0);
//				default:
//					break;
//				}
//				
//				p.rect((float)(350-100*(c-j)), (float)(220-100*(r-i)), 100, 100);
			}
		}
		
//		for (int i = y-6; i < y+6; i++) {
//			for (int j = x-6; j < x+6; j++) {
//		
//				switch (bl[i][j]) {
//				case 0:
//					p.noFill();
//					break;
//				case 1:
//					p.fill(0,0,0,100);
//					break;
//				default:
//					break;
//				}
//				p.rect((float)(350-100*(c-j)), (float)(220-100*(r-i)), 100, 100);
//		
//			}
//		}
		
	}
	
	
	public void dispObjects(int[][]ob, double r, double c) {
		int x = (int)(Math.round(c));
		int y = (int)(Math.round(r));
		p.noStroke();
		for (int i = y-6; i < y+6; i++) {
			for (int j = x-6; j < x+6; j++) {
				if (ob[i][j] < 3 && ob[i][j] > 0) {
					tile = objectTiles[ob[i][j]-1];
					p.image(tile, (float)(350-100*(c-j)), (float)(220-100*(r-i)));
				} else if (ob[i][j] > 2) {
					tile = objectTiles[ob[i][j]-2];
					p.image(tile, (float)(350-100*(c-j)), (float)(220-100*(r-i)));
				}
				
				
				
//				float X = (float) (350-100*(c-j)); 
//				float Y =  (float) (220-100*(r-i));
//				switch (ob[i][j]) {
//				case 0:
//					p.noFill();
//					break;
//				case 1:
//					p.fill(0,255,0,100);
//					p.ellipse(X + 150, Y + 150, 250, 250);
//					break;
//				case 2:
//					p.fill(255,0,0,100);
//					p.ellipse(X+50,Y+50,75,75);
//					break;
//				case 4:
//					p.fill(50,25,10,100);
//					p.rect(X+10, Y, 80,100);
//					break;
//				case 5:
//					p.fill(50,25,10,100);
//					p.rect(X+25,Y+25,150,150);
//					break;
//				case 6:
//					p.fill(0,255,100,100);
//					p.rect(X+10,Y+10,80,80);
//				default:
//					break;
//				}
			}
		}
	}

	
}
