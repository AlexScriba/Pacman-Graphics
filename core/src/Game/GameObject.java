package Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameObject {
	private Tuple position;
	private final char symbol;
	private Texture texture;

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

	
	
	public void setPosition(Tuple t) {
		position = t;
	}

	// Alex and Anushka will write methods using Assets
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
}
