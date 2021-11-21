package Game;

import java.util.Random;
/**
 * 
 * An Enum class in which 4 directions (UP, DOWN, LEFT, RIGHT) are defined.
 *
 */
public enum Direction {

	UP, LEFT, DOWN, RIGHT;
	
	private static Direction[] OPPOSITE_DIR = { DOWN, RIGHT, UP, LEFT };
	
	public Direction opposite() {
		return OPPOSITE_DIR[ordinal()];
	}

	public static Direction random() {
		return values()[new Random().nextInt(4)];
	}
	
	public Tuple DirectionToTuple() {
		if(this == UP) {
			return new Tuple(0, -1);
		}
		
		else if(this == DOWN) {
			return new Tuple(0, 1);
		}
		
		else if(this == LEFT) {
			return new Tuple(-1, 0);
		}
		
		else {
			return new Tuple(1, 0);
		}
	}
	
	public static Direction tupleToDirection(Tuple tuple) {
		if (tuple.equals(new Tuple(0,-1)))
		{
			return UP;
		}
		else if (tuple.equals(new Tuple(0,1)))
		{
			return DOWN;
		}
		else if (tuple.equals(new Tuple(-1,0)))
		{
			return LEFT;
		}
		else
		{
			return RIGHT;
		}
	}
	
	
	
}
