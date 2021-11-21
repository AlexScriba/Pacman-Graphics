package Game;


public class ScatterBottomLeftCorner implements GhostAlgorithm {

	@Override
	public void behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
	
		// Clyde (Orange) algorithm
		PacMan pacman = PacMan.getInstance();
		Maze maze = Maze.getInstance();
		if ((double)pacman.getFood()/(double)maze.getTotalNumOfFood() <= (double)1/(double)3) {
			return;
		}
		
		Tuple targetTile = maze.getBottomLeftCorner();
		g.moveToTarget(sa, targetTile, doReverse);
	}

}
