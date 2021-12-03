package Game;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Main {

	public static void main(String[] args) {
		
		Game game = Game.getInstance();
		// Starting the game loop
		boolean repeat = true;
		Scanner s = new Scanner(System.in);
		System.out.println("Please, specify your name:");
		String name = s.next();
		while(repeat) {
			System.out.println("Please specify the difficulty (Easy, Medium, Hard):");
			String difficulty = s.next();
			
			int res = game.gameInit(difficulty, "Scatter");
			while(res != 0) {
				if(res == 1) { // Handling Difficulty error
					System.out.println("Please specify the difficulty properly (Easy, Medium, Hard):");
					difficulty = s.next();
					res = game.gameInit(difficulty, "Scatter");
				} else if(res == 2) {
					System.out.println("Game mode init failed!");
					s.close();
					return;	
				}
			}
			
			while (game.isGameOver()==false) {
				System.out.println(game.printMaze());
				System.out.println("\n\nType in a command (Up, Down, Left, Right, Reset): ");
				String cmd = s.next();
				game.gameTick(cmd);
			}
			printScore("score.txt", PacMan.getInstance().getScore(), name, difficulty);
			System.out.println("Do you want to play again?");
			String answer = s.next();
			repeat = answer.equals("Yes");
			game.gameOver();
		}
		System.out.println("Out of the loop");
		s.close();
		
		
	}
	
	public static boolean printScore(String filename, int score, String name, String difficulty) {
		try {
			PrintWriter myWriter = new PrintWriter(new FileWriter(filename, true));
			//FileWriter myWriter = new FileWriter(filename);
			String line = "Difficulty:" + difficulty+" Name:" + name+ " Score:" + score+" \n";
	        //myWriter.write(line);
	        myWriter.append(line);
	        myWriter.close();
	        System.out.println(line);
	        return true;
		} catch(IOException e) {
			System.out.printf("The file %s does not exist or cannot be accessed!", filename);
			e.printStackTrace();
			return false;
		}
	}
	
}






/*
 * 1) How can we choose which maze we want for the difficulty modes? (done)
 * 2) Scoring system
 * 3) Refractor Maze.java -> it has to scan in the file (done)
 * 4) A_star and Greedy should return direction
 * 5) A_star, Greedy, Random should inherit the same class
 * 6) resolveCollision(Ghost g) -> Frightened accepts ghosts, and usses it; the other ones -> no (done)
 * 7) move the start postiion to moveable instead of game oBject (done)
 * solution: do not use ghost; return boolean; handle these functions in Game
 * 8) Make the Maze file recognize the H block -> initialize Ghost and PacMan positions based on this
 * 9) Each Algorithm should hold a reference of the appripriate Ghost
 * the .txt files: map (with H block), and a file containing configurations (algorithm + ghost) -> 2 in total 
 * 
 * 
 *
 */
