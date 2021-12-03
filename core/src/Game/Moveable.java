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
	
	/**
	 * return new position
	 * */
	public Tuple move() {
			this.setPosition(getTuple().sum(getDirection().DirectionToTuple()));
			return this.getTuple();
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public boolean resetDirection() {
		direction = init_direction;
		return true;
	}
	
	public boolean resetPosition() {		
		return this.setPosition(init_position);
	}
}
