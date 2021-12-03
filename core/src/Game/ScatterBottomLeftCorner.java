package Game;


public class ScatterBottomLeftCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
	
		// Clyde (Orange) algorithm
		PacMan pacman = PacMan.getInstance();
		Maze maze = Maze.getInstance();
		if ((double)pacman.getFood()/(double)maze.getTotalNumOfFood() <= (double)1/(double)3) {
			return g.getTuple();
		}
		
		Tuple targetTile = maze.getBottomLeftCorner();
		
		return targetTile;
	}
	
	

}
