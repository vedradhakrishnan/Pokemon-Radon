import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PFont;

public class Runner extends PApplet {
	
	final int MAP_WIDTH = 972;
	final int MAP_HEIGHT = 972;
	PFont font;
	
	boolean showWholeMap = false;
	int tile;
	int sectR = 26;
	int sectC = 0;
	
	FileWriter fw;
	Scanner sc;
	
	int[][] base;
	int[][] blocks;
	int[][] objects;
	
	public void settings() {
		size(1080,720);
	}
	
	public void setup() {
		try {
			sc = new Scanner(new File("base.txt"));
			base = new int[MAP_HEIGHT][MAP_WIDTH];

			for (int i = 0; i < base.length && sc.hasNextLine(); i++) {
				for (int j = 0; j < base[i].length && sc.hasNext(); j++) {
					base[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			base = new int[MAP_HEIGHT][MAP_WIDTH];
		}
		try {
			sc = new Scanner(new File("blocks.txt"));
			blocks = new int[MAP_HEIGHT][MAP_WIDTH];

			for (int i = 0; i < blocks.length && sc.hasNextLine(); i++) {
				for (int j = 0; j < blocks[i].length && sc.hasNext(); j++) {
					blocks[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			blocks = new int[MAP_HEIGHT][MAP_WIDTH];
		}
		
		try {
			sc = new Scanner(new File("objects.txt"));
			objects = new int[MAP_HEIGHT][MAP_WIDTH];

			for (int i = 0; i < objects.length && sc.hasNextLine(); i++) {
				for (int j = 0; j < objects[i].length && sc.hasNext(); j++) {
					objects[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			objects = new int[MAP_HEIGHT][MAP_WIDTH];
		}
		font = createFont("Arial",30);
		textFont(font);
	}
	
	public void draw() {
		if (!showWholeMap) {
			dispMapSect();
		} else {
			dispMapWhole();
		}
		if (tile == 65) {
			showWholeMap = !showWholeMap;
			tile = 1;
		} else if (tile == 10){
			saveToFiles();
			tile = 1;
		} else if (37 <= tile  && tile <= 40) {
			chageSect();
			tile = 1;
		}
		
		if (mousePressed && mouseInBounds()) {
			changeValue();
		}
		
		fill(0);
		text("["+sectC+", "+sectR+"]", 20, 40);
	}
	
	private boolean mouseInBounds() {
		return 0 < mouseX && 0 < mouseY && mouseX < width && mouseY < height;
	}

	private void chageSect() {
		if (tile == 37 && sectC > 0) {
			sectC--;
		} else  if (tile == 38 && sectR > 0) {
			sectR--;
		} else  if (tile == 39 && sectC < 17) {
			sectC++;
		} else  if (tile == 40 && sectR < 26) {
			sectR++;
		}
	}

	private void saveToFiles() {
		String s = "";
		try {
			fw = new FileWriter("generatedBase.txt", false);
			for (int i = 0; i < base.length; i++) {				
				s = "";
				for (int j = 0; j < base[i].length; j++) {
					s += base[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter("generatedBlocks.txt", false);
			for (int i = 0; i < blocks.length; i++) {				
				s = "";
				for (int j = 0; j < blocks[i].length; j++) {
					s += blocks[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fw = new FileWriter("generatedObjects.txt", false);
			for (int i = 0; i < objects.length; i++) {				
				s = "";
				for (int j = 0; j < objects[i].length; j++) {
					s += objects[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("file saved");
	}

	private void dispMapWhole() {
		background(0);
		noStroke();
		for (int i = 0; i < base.length; i++) {
			for (int j = 0; j < base[0].length; j++) {
				switch (base[i][j]) {
				case 0:
					fill(66,135,245);
					break;
				case 1:
					fill(66,245,135);
					break;
				case 2:
					fill(248, 255, 115);
					break;
				case 3:
					fill(135,135,135);
					break;
				case 4:
					fill(0,156,136);
					break;
				case 5:
					fill(200,170,111);
					break;
				case 6:
					fill(245,250,255);
					break;
				case 7:
					fill(0,0,0);
					break;
				default:
					break;
				}
				
				rect((float)(j)*0.74f,(float)(i)*0.74f,0.74f,0.74f);
				
				switch (blocks[i][j]) {
				case 0:
					noFill();
					break;
				case 1:
					fill(0,0,0,100);
					break;
				default:
					break;
				}
				
				rect((float)(j)*0.74f,(float)(i)*0.74f,0.74f,0.74f);
			}
		}
		
	}

	private void dispMapSect() {
		background(0);
		stroke(0);
		int startR = (int) (Math.round((double)(sectR*MAP_HEIGHT)/27.0));
		int stopR = startR + 36;
		int startC = (int) (Math.round((double)(sectC*MAP_WIDTH)/18.0));
		int stopC = startC + 54;
		//System.out.println(startR+","+stopR+","+startC+","+stopC);
		for (int i = startR; i < stopR; i++) {
			for (int j = startC; j < stopC; j++) {
				switch (base[i][j]) {
				case 0:
					fill(66,135,245);
					break;
				case 1:
					fill(66,245,135);
					break;
				case 2:
					fill(248, 255, 115);
					break;
				case 3:
					fill(135,135,135);
					break;
				case 4:
					fill(0,156,136);
					break;
				case 5:
					fill(200,170,111);
					break;
				case 6:
					fill(245,250,255);
					break;
				case 7:
					fill(0,0,0);
					break;
				default:
					break;
				}
				
				rect((j-startC)*20, (i-startR)*20, 20, 20);
				
				switch (blocks[i][j]) {
				case 0:
					noFill();
					break;
				case 1:
					fill(0,0,0,100);
					break;
				default:
					break;
				}
				
				rect((j-startC)*20, (i-startR)*20, 20, 20);
				
			}
		}
		for (int i = startR; i < stopR; i++) {
			for (int j = startC; j < stopC; j++) {
				int x = (j-startC)*20;
				int y = (i-startR)*20;
				switch (objects[i][j]) {
				case 0:
					noFill();
					break;
				case 1:
					fill(0,255,0,100);
					ellipse(x + 30, y + 30, 50, 50);
					break;
				case 2:
					fill(255,0,0,100);
					ellipse(x+10,y+10,15,15);
					break;
				case 4:
					fill(50,25,10,100);
					rect(x+5, y, 10,20);
					break;
				case 5:
					fill(50,25,10,100);
					rect(x+5,y+5,30,30);
					break;
				case 6:
					fill(0,255,100,100);
					rect(x+2,y+2,16,16);
				default:
					break;
				}
			}
		}
	}

	public void keyPressed() {
		System.out.println(keyCode);
		tile = keyCode;
	}
	
	public void mousePressed() {
//		changeValue();
	}

	private void changeValue() {
		int x = (int) (sectC*54 + Math.floor((double)(mouseX)/20.0));
		int y = (int) (sectR*36 + Math.floor((double)(mouseY)/20.0));
		if (tile >= 48 && tile <= 55) {
			base[y][x] = tile-48;
		} else {
			if (tile == 73) {
				blocks[y][x] = 1;
			} else if (tile == 79) {
				blocks[y][x] = 0;
			} else if (tile >= 70 && tile <= 76) {
				objects[y][x] = tile-70;
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("Runner");

	}

}
