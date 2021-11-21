package Game;
//change
public abstract class Moveable extends GameObject {
	
	private Direction direction;
	private Direction init_direction;
	private Tuple init_position;
	
	public Moveable(int x_coordinate, int y_coordinate, char mark, Direction d) {
		super(x_coordinate, y_coordinate, mark);
		init_direction = d;
		init_position = new Tuple(x_coordinate, y_coordinate);
		direction = d;
	}
	
	public void move() {
			this.setPosition(getTuple().sum(getDirection().DirectionToTuple()));
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public void resetDirection() {
		direction = init_direction;
	}
	
	public void resetPosition() {		
		this.setPosition(init_position);
	}
}
