package Game;

import com.badlogic.gdx.graphics.Texture;

public class TexturedMoveable extends Moveable {
	private Texture texture;
	
	public TexturedMoveable(int x_coordinate, int y_coordinate, char mark, Direction d) {
		super(x_coordinate, y_coordinate, mark, d);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture tex) {
		texture = tex;
	}
}
