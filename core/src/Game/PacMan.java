package Game;

import java.io.File;
import java.util.Scanner;

public class PacMan extends Moveable {
	private static PacMan instance; // we have only 1 PacMan
	int lives;
	int init_lives;
	private int score;
	int food;

	private PacMan(int x_coordinate, int y_coordinate, int lives) {
		super(x_coordinate, y_coordinate, 'P', Direction.RIGHT);
		this.lives = lives;
		this.init_lives = lives;
		this.food = 0;
		this.score=0;
	}

	
	
	public static boolean initPacMan(String filepath) {
		if(instance == null) {
			try {
				File file = new File(filepath);
				Scanner scanner = new Scanner(file);

				int x = Integer.parseInt(scanner.nextLine());
				int y= Integer.parseInt(scanner.nextLine());
				int lives = Integer.parseInt(scanner.nextLine());
				instance = new PacMan(x, y, lives);
				scanner.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}		
		} else {
			System.out.println("PacMan has already been initiliazed");
			return true;
		}
		
	}
	
	public static void destroyPacman() {
		instance = null;
	}
	
	public void resetPacMan() {
		this.resetPosition();
		this.resetDirection();
		lives = init_lives;
		food = 0;
		score = 0;
	}

	public static PacMan getInstance() {
		return instance;
	}

	public void changeScore(int change) {
		score += change;
	}

	public int getFood() {
		return food;
	}


	public void changeLives(int deltaLife) {
		lives += deltaLife;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementFood() {
		food++;
	}


}
