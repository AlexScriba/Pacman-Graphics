package Game;

public class ChaseAggressive implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {

		// The target tile is: pacman's position
		
		// Getting the position of Pac-Man
		PacMan pacman = PacMan.getInstance();
		Tuple targetTile = pacman.getTuple();
		
		return targetTile;

	}
	
	
}
