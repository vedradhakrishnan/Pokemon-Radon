import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PFont;

public class Runner extends PApplet {

	//all da variables
	String guiState = "intro";
	Scanner scanner;
	ArrayList<String> textQueue = new ArrayList<String>();
	PFont font;
	Intro intro;
	boolean textBox = true;
	Title title;
	PlayerSprite ps;
	MapSprite ms;
	final int MAP_WIDTH = 350;
	final int MAP_HEIGHT = 175;
	String name;
	double playerX;
	double playerY;
	int playerDir;
	int[][] mapBase = new int[MAP_HEIGHT][MAP_WIDTH];
	int[][] mapBlocks = new int[MAP_HEIGHT][MAP_WIDTH];
	int[][] mapObjects = new int[MAP_HEIGHT][MAP_WIDTH];
	
	public void settings() {
		size(800,700);
	}
	
	public void setup() {
		background(0,0,50);
		font = createFont("Courier", 50);
		intro = new Intro(this);
		title = new Title(this);
		ps = new PlayerSprite(this);
		ms = new MapSprite(this);

		try {
			scanner = new Scanner(new File("gamefiles/game/player/info.txt"));
			name = scanner.next();
			playerX = scanner.nextInt();
			playerY = scanner.nextInt();
			playerDir = scanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			scanner = new Scanner(new File("gamefiles/game/map/base.txt"));
			for (int i = 0; i < mapBase.length; i++) {
				for (int j = 0; j < mapBase[i].length; j++) {
					mapBase[i][j] = scanner.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			scanner = new Scanner(new File("gamefiles/game/map/blocks.txt"));
			for (int i = 0; i < mapBlocks.length; i++) {
				for (int j = 0; j < mapBlocks[i].length; j++) {
					mapBlocks[i][j] = scanner.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			scanner = new Scanner(new File("gamefiles/game/map/objects.txt"));
			for (int i = 0; i < mapObjects.length; i++) {
				for (int j = 0; j < mapObjects[i].length; j++) {
					mapObjects[i][j] = scanner.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ps.dir = playerDir;
	}
	
	public void draw() {
		
		switch (guiState) {
		case "intro":
			dIntro();
			break;
		case "title":
			dTitle();
			break;
		case "game":
			dGame();
			break;
		default:
			break;
		}
		if (textBox) {
			dlogBox();
			dlogText();
		}
	}
	


	public void keyPressed() {
		char k;
		switch (keyCode) {
		case 8:
			k = '<';
			break;
		case 37:
			k = 'a';
			break;
		case 38:
			k = 'w';
			break;
		case 39:
			k = 'd';
			break;
		case 40:
			k = 's';
			break;
		default:
			k = key;
			break;
		}
//		System.out.println(keyCode);
		switch (guiState) {
		case "intro":
			kIntro(k);
			break;
		case "title":
			kTitle(k);
			break;
		case "game":
			kGame(k);
			break;
		default:
			break;
		}
	}
	
	private void kGame(char k) {
		ps.updateA(k);
		ps.updated = true;
		if (intSpace()) {
			ps.updateD(k);
			playerDir = ps.dir;
		}
		
		if (playerDir == 0 && intSpace() && !blockInDir(playerDir)) {
			playerY += 0.1;
		} else if (playerDir == 1 && intSpace() && !blockInDir(playerDir)) {
			playerX -= 0.1;
		} else if (playerDir == 2 && intSpace() && !blockInDir(playerDir)) {
			playerX += 0.1;	
		} else if (playerDir == 3 && intSpace() && !blockInDir(playerDir)) {
			playerY -= 0.1;
		}
		
	}
	
	private boolean blockInDir(int d) {
		switch (d) {
		case 0:
			//System.out.println(mapBlocks[(int) Math.round(playerY) + 1][(int) Math.round(playerX)] != 0);
			return mapBlocks[(int) Math.floor(playerY) + 1][(int) Math.round(playerX)] != 0;
		case 1:
			return mapBlocks[(int) Math.round(playerY)][(int) Math.ceil(playerX) - 1] != 0;
		case 2:
			return mapBlocks[(int) Math.round(playerY)][(int) Math.floor(playerX) + 1] != 0;
		case 3:
			return mapBlocks[(int) Math.ceil(playerY) - 1][(int) Math.round(playerX)] != 0;
		default:
			return true;
		}
	}

	private boolean intSpace() {
		if (Math.abs(Math.round(playerX) - playerX) < 0.07 && Math.abs(Math.round(playerY) - playerY) < 0.07) {
			playerX = Math.round(playerX);
			playerY = Math.round(playerY);
		}
		//System.out.println("["+(Math.abs(Math.round(playerX) - playerX) < 0.15)+","+ (Math.abs(Math.round(playerY) - playerY)) + "]");
		return Math.abs(Math.round(playerX) - playerX) < 0.07 && Math.abs(Math.round(playerY) - playerY) < 0.07;
	}

	private void dGame() {
		background(0);
		if (playerDir == 0 && !intSpace() && !blockInDir(playerDir)) {
			playerY += 0.1;
		} else if (playerDir == 1 && !intSpace() && !blockInDir(playerDir)) {
			playerX -= 0.1;
		} else if (playerDir == 2 && !intSpace() && !blockInDir(playerDir)) {
			playerX += 0.1;	
		} else if (playerDir == 3 && !intSpace() && !blockInDir(playerDir)) {
			playerY -= 0.1;
		}
		ms.dispBase(mapBase, mapBlocks, playerY, playerX);
		ms.dispObjects(mapObjects, playerY, playerX);
		ps.count++;
		if (!keyPressed) {
			ps.updateA();
		}
		ps.sprite(); 
		
		border();
	}
	
	private void border() {
		noStroke();
		fill(0);
		rect(0, height-265, width, 265);
		rect(0, 0, 15, height);
		rect(0, 0, width, 15);
		rect(width-15, 0, 15, height);
		
	}

	private void kTitle(char k) {
		if (title.count > 150) {
			textBox = true;
			if (true) {
				guiState = "game";
			} else {
				guiState = "name";
			}
			
		}
		
	}

	private void dTitle() {
		background(0);
		if (title.count == 0) {
			textBox = false;
		}
		title.disp();
		title.count++;
	}
	
	private void kIntro(char k) {
		textQueue.remove(0);
		if (k == '<') {
			System.out.println("djsfsk");
		}
	}

	private void dlogBox() {
		noStroke();
		fill(20,69,120);
		ellipse(width/2 - 370, height - 30, 40, 40);
		ellipse(width/2 + 370, height - 30, 40, 40);
		ellipse(width/2 - 370, height - 230, 40, 40);
		ellipse(width/2 + 370, height - 230, 40, 40);
		rect(width/2 - 370, height - 250, 740, 240);
		rect(width/2 - 390, height - 230, 780, 200);
		fill(255);
		ellipse(width/2 - 370, height - 30, 20, 20);
		ellipse(width/2 + 370, height - 30, 20, 20);
		ellipse(width/2 - 370, height - 230, 20, 20);
		ellipse(width/2 + 370, height - 230, 20, 20);
		rect(width/2 - 370, height - 240, 740, 220);
		rect(width/2 - 380, height - 230, 760, 200);
	}
	
	private void dlogText() {
		if (textQueue.size() > 0) {
			String s = textQueue.get(0);
			font = createFont("Courier", 30);
			textFont(font);
			fill(0);
			text(s, 30, 470, 750, 200); 
		}
	}

	private void dIntro() {
		background(0);
		if(textQueue.size() == 0 && !intro.transferred) {
			intro.loadIntro(scanner);
			intro.addToQueue(textQueue);
			intro.transferred = true;
		} else if(textQueue.size() == 0 && intro.transferred) {
			guiState = "title";
		}
	}

	public static void main(String[] args) {
		PApplet.main("Runner");
	}

}
