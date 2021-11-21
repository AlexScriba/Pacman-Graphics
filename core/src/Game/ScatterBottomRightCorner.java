package Game;

public class ScatterBottomRightCorner implements GhostAlgorithm {

	@Override
	public void behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		
		PacMan pacman = PacMan.getInstance();
		if (pacman.getFood() < 30) {
			return;
		}
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getBottomRightCorner();
		g.moveToTarget(sa, targetTile, doReverse);
	}

}
