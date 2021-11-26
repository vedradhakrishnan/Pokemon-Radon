import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

public class Intro {

	private PApplet p;
	public boolean transferred;
	private ArrayList<String> text;
	
	public Intro(PApplet app) {
		p = app;
		transferred = false;
		text = new ArrayList<String>();
	}

	public void loadIntro(Scanner s) {
		try {
			s = new Scanner(new File("gamefiles/intro/intro.txt"));
			while(s.hasNextLine()) {
				text.add(s.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.close();
		
	}

	public void addToQueue(ArrayList<String> q) {
		for (int i = 0; i < text.size(); i++) {
			q.add(text.get(i));
		}
	}
	
	
}
