package Game;

public abstract class GameObject {
	private Tuple position;
	private final char symbol;
	private Sprite asset;

	public GameObject(int x_coordinate, int y_coordinate, char symbol) {
		this.symbol = symbol; // objects will not change their symbols during the game
		// asset is automatically set to null
		position = new Tuple(x_coordinate, y_coordinate);
	}

	

	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Returns this GameObject's position in the form  of Tuple.
	 * @return Tuple a Tuple (x_coordinate, y_coordinate)
	 */
	public Tuple getTuple() {
		return position;
	}

	
	
	public boolean setPosition(Tuple t) {
		if(t == null)
			return false;
		position = t;
		return true;
	}

	// Alex and Anushka will write methods using Assets

}
