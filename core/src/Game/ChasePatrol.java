package Game;


public class ChasePatrol implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g) {
		// Pacman position + 2 next tiles
		// draw a vector from red's position to this tile
		// and then doubling the length of the vector
		// The tile that this new, extended vector ends on will be the actual target.

		// dont move if pacman hasnt eaten
		// at least 30 of the dots

		// Check if Pacman ate 30 food items
		PacMan pacman = PacMan.getInstance();
		if (pacman.getFood() < 30) {
			return g.getTuple();
		}

		// The target tile is: pacman's position + 2 next tiles
		Tuple twoTilesAHead = null;
		if(pacman.getDirection() == Direction.UP) {
			twoTilesAHead = Tuple.getXTilesAHead(2, pacman.getDirection(), pacman.getTuple());
			twoTilesAHead = Tuple.getXTilesAHead(2, Direction.LEFT, twoTilesAHead);
		} else {
			twoTilesAHead = Tuple.getXTilesAHead(2, pacman.getDirection(), pacman.getTuple());
		}

		// get red ghost's position
		Ghost red = Ghost.getGhostByName('R');
		Tuple redPosition = red.getTuple();
		Tuple targetTile = twoTilesAHead.sum(twoTilesAHead.difference(redPosition));

		return targetTile;
	}
	
	
}
