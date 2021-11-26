import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner sc;
		FileWriter fw;
		
		final int MAP_HEIGHT = 972;
		final int MAP_WIDTH = 972;
		
		final int xSt = 0;
		final int xSp = 350;
		final int ySt = 740;
		final int ySp = 920;
		
		int[][] base;
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
		int[][] blocks;
		try {
			sc = new Scanner(new File("blocks.txt"));
			blocks = new int[MAP_HEIGHT][MAP_WIDTH];
			
			for (int i = 0; i < blocks.length& sc.hasNextLine(); i++) {
				for (int j = 0; j < blocks[i].length && sc.hasNext(); j++) {
					blocks[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			blocks = new int[MAP_HEIGHT][MAP_WIDTH];
		}
		int[][] objects;
		try {
			sc = new Scanner(new File("objects.txt"));
			objects = new int[MAP_HEIGHT][MAP_WIDTH];
			
			for (int i = 0; i < objects.length& sc.hasNextLine(); i++) {
				for (int j = 0; j < objects[i].length && sc.hasNext(); j++) {
					objects[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			objects = new int[MAP_HEIGHT][MAP_WIDTH];
		}
		
		
		
		
		
		
		
		
		
		
		String s = "";
		try {
			fw = new FileWriter("generatedBase.txt", false);
			for (int i = ySt; i < ySp; i++) {				
				s = "";
				for (int j = xSt; j < xSp; j++) {
					s += base[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter("generatedBlocks.txt", false);
			for (int i = ySt; i < ySp; i++) {				
				s = "";
				for (int j = xSt; j < xSp; j++) {
					s += blocks[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fw = new FileWriter("generatedObjects.txt", false);
			for (int i = ySt; i < ySp; i++) {				
				s = "";
				for (int j = xSt; j < xSp; j++) {
					s += objects[i][j] + " ";
				}
				fw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("file saved");
	}

}
