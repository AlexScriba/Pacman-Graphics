package Game;


public class ChaseAmbush implements GhostAlgorithm {
	
	/**
	 * This method instructs a ghost to move close to Pacman.
	 * @param g the ghost to instruct
	 * @return void
	 */
	@Override
	public void behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		
		// Get 4 Tiles ahead of Pacman
		PacMan pacman = PacMan.getInstance();
	
		//the target tile is:
		//pacman's position + 4 next tiles
		
		Tuple targetTile = null;
		if(pacman.getDirection() == Direction.UP) {
			targetTile = Tuple.getXTilesAHead(4, pacman.getDirection(), pacman.getTuple());
			targetTile = Tuple.getXTilesAHead(4, Direction.LEFT, targetTile);
		} else {
			targetTile = Tuple.getXTilesAHead(4, pacman.getDirection(), pacman.getTuple());
		}
		
		g.moveToTarget(sa, targetTile, doReverse);

	}

}
