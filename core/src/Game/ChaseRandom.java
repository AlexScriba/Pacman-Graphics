package Game;


public class ChaseRandom implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
//		Chase random: 
//		check the distance between pacman and orange in 8 tile radius
//		>= 8 tiles, go to pacman's position
//		<8 tiles, go to bottom left corner
		
		// Don't move unless 3rd the food has been eaten
		PacMan pacman = PacMan.getInstance();
		Maze maze = Maze.getInstance();
		System.out.printf("The total number of food is %d and Pacman ate %d\n", maze.getTotalNumOfFood(), pacman.getFood());
		if ((double)pacman.getFood()/(double)maze.getTotalNumOfFood() <= (double)1/(double)3) {
			return g.getTuple();
		}

		Tuple pacmanPos = pacman.getTuple();
		
		double distance = Tuple.distance(pacmanPos, g.getTuple());
		Tuple targetTile = null;
		
		if (distance >= 8) {
			//chase pacman
			targetTile = pacmanPos;
		}
		else {
			//go to bottom left corner
			targetTile = maze.getBottomLeftCorner();
		}
		
		return targetTile;
	}
	
	

}
