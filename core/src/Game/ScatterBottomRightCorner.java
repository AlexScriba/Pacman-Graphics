package Game;

public class ScatterBottomRightCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
		
		PacMan pacman = PacMan.getInstance();
		if (pacman.getFood() < 30) {
			return g.getTuple();
		}
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getBottomRightCorner();
		
		return targetTile;
	}
	
	
}
