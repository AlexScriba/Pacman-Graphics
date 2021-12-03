package Game;

import java.util.*;
import java.io.File;

public class Ghost extends  TexturedMoveable {

	private final static ArrayList<Ghost> ghosts=new ArrayList<Ghost>();
	/**
	 * get ghost data from txt file
	 * @param filepath the path to the ghostStartData file
	 * @return boolean whether the initialization is successful
	 */
	public static boolean initGhosts(String filepath) {
		File file = null;
		Scanner scanner = null;
		try {
			file = new File(filepath);
			scanner = new Scanner(file);

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
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
	

	public static boolean destroyGhosts() {
		ghosts.clear();

		return ghosts.isEmpty();
	}
	
	
	/**
	 * This returns the ArrayList<Ghost> in the Ghost static field
	 * @return ArrayList<Ghost>
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
//		if (ghosts == null) {
//			return null;
//		}

		return ghosts.get(index);
	}
	
	public static Ghost getGhostByName(char key) {
//		if (ghosts == null) {
//			return null;
//		}

		for(Ghost g : ghosts) {
			if(g.getSymbol() == key) 
				return g;
		}
		return null;
	}


	/**
	 * return false if there's no ghost to reverse
	 * */
	public static boolean reverseDirectionsOfAllGhosts() {
		boolean result = false;
		for (int i = 0; i < ghosts.size(); i++) {
			Direction opposite = ghosts.get(i).getDirection().opposite();
			ghosts.get(i).setDirection(opposite);
			result = true;
		}
		return result;
	}


	public Ghost(int x, int y, char symbol) {
		super(x, y, symbol, Direction.UP);

	}
	
	
	public static boolean resetGhosts() { // This is stupid but the requirement is stupid
//		if(ghosts == null)
//			return false;
		for(Ghost g: ghosts) {
//			if(!g.resetPosition() || !g.resetDirection())
			    g.resetPosition();
			    g.resetDirection();
//				return false;
		}
		return true;
	}

	
	//logic: If ghost actually moves away from tile, return true
	//if the ghost stays at the same tile, return false
	public boolean moveToTarget(SearchAlgorithm sa, Tuple targetTile, boolean doReverse){
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
			// if not in reverse mode, the ghosts will never go to the opposite direction of the current direction
			// i.e. when the Direction dir is equal to the opposite current direction, we exclude it 
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
			return true;
		}
		return false;
	}
}
