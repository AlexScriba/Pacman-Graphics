package Game;

import java.util.*;
import java.io.File;

public class Ghost extends  Moveable {

	private final static ArrayList<Ghost> ghosts=new ArrayList<Ghost>();
	/**
	 * get ghost data from txt file
	 * @param filepath the path to the ghostStartData file
	 * @return void
	 */
	public static boolean initGhosts(String filepath) {

		try {
			File file = new File(filepath);
			Scanner scanner = new Scanner(file);

			int numGhosts = Integer.parseInt(scanner.nextLine());

			for (int i = 0; i < numGhosts; i++) {
				String name = scanner.nextLine();
				int x = Integer.parseInt(scanner.nextLine());
				int y = Integer.parseInt(scanner.nextLine());

				ghosts.add(new Ghost(x, y, name.charAt(0)));
			}

			scanner.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void destroyGhosts() {
		ghosts.clear();
	}
	
	
	/**
	 * This returns the ArrayList<Ghost> in the Ghost static field
	 * @return
	 */
	public static ArrayList<Ghost> getGhosts() {
		return ghosts;
	}
	/**
	 * Get the ghost object from the static ArrayList ```ghosts``` based on the index given
	 * @param index the index of the ghost object in the ArrayList<Ghost> (index starts from 0)
	 * @return Ghost a Ghost object
	 */
	public static Ghost getGhost(int index) {
		if (ghosts == null) {
			return null;
		}

		return ghosts.get(index);
	}
	
	public static Ghost getGhostByName(char key) {
		if (ghosts == null) {
			return null;
		}

		for(Ghost g : ghosts) {
			if(g.getSymbol() == key) 
				return g;
		}
		return null;
	}


	public static void reverseDirectionsOfAllGhosts() {
		for (int i = 0; i < ghosts.size(); i++) {
			Direction opposite = ghosts.get(i).getDirection().opposite();
			ghosts.get(i).setDirection(opposite);
		}
	}


	public Ghost(int x, int y, char symbol) {
		super(x, y, symbol, Direction.UP);

	}
	
	
	public static void resetGhosts() {
		for(Ghost g: ghosts) {
			g.resetPosition();
			g.resetDirection();
		}
	}

	public void moveToTarget(SearchAlgorithm sa, Tuple targetTile, boolean doReverse){
		ArrayList<Tuple> points = new ArrayList<Tuple>();
		points.add(this.getTuple());
		points.add(targetTile);
		Direction nextDirection = null;
		ArrayList<Direction> accessibleDirections=new ArrayList<Direction>();
		Direction[] allDirections=Direction.values();

		if(doReverse){
			for(Direction dir:allDirections){
				accessibleDirections.add(dir);
			}
		}
		else{
			for(Direction dir: allDirections){
				if(!dir.equals(getDirection().opposite())){
					accessibleDirections.add(dir);
				}
			}
			
		}

		nextDirection = sa.getNextDirection(points, accessibleDirections);
		if(nextDirection!=null){
			this.setDirection(nextDirection);
			this.move();
		}
	}

	

}
