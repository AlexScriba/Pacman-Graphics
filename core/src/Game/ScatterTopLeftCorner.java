package Game;



public class ScatterTopLeftCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
		
		/**
		 * pink ghost behavior
		 * */
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getTopLeftCorner();
		
		return targetTile;
	}
	
	


}
