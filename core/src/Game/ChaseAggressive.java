package Game;

public class ChaseAggressive implements GhostAlgorithm {

	@Override
	public void behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		// TODO Auto-generated method stub
		

		// The target tile is: pacman's position
		
		// Getting the position of Pac-Man
		PacMan pacman = PacMan.getInstance();
		Tuple targetTile = pacman.getTuple();
		
		
		g.moveToTarget(sa, targetTile, doReverse);
		
		//this is red ghost 
		//so we need to update red ghost's position in maze
//		Maze maze = Maze.getInstance();
//		maze.setRedGhostPosition(g.getTuple());

	}

}
