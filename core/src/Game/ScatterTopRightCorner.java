package Game;


public class ScatterTopRightCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getTopRightCorner();
		
		return targetTile;
	}

}
