package Game;

import java.util.ArrayList;

/**
 * 
 * This class represents a position in maze. Tuple(first=x_coordinate, second=y_coordinate)
 *
 */
public class Tuple {
	private int first;
	private int second;
	public Tuple(int first, int second) {
		this.first=first;
		this.second=second;
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
	
	public static int tuppleToInt(Tuple t, int k) {
		return t.second*k+t.first;
		
	}
	
	public Tuple difference(Tuple t) {
		return new Tuple(this.first-t.first, this.second-t.second);
	}
	
	public Tuple sum(Tuple t) {
		return new Tuple(this.first + t.first, this.second + t.second);
	}
	
	/**
	 * return position that is x tiles a head of PacMan
	 * */
	public static Tuple getXTilesAHead(int x, Direction currentDirection, Tuple currentPos) {
		int currentX = currentPos.getFirst();
		int currentY = currentPos.getSecond();

		switch(currentDirection) {
			case UP: 
				currentY-=x;
				break;
			case RIGHT:
				currentX+=x;
				break;
			case DOWN:
				currentY+=x;
				break;
			default:
				currentX-=x;
				break;
		}
				
		return new Tuple(currentX, currentY);
	}
	
	/**
	 * distance (in tiles) between 2 tuples
	 * */
	public static double distance(Tuple tuple1, Tuple tuple2) {
		int x_distance = Math.abs(tuple2.first - tuple1.first);
		int y_distance = Math.abs(tuple2.second - tuple1.second);
		
		double distance = Math.sqrt(x_distance*x_distance + y_distance*y_distance);
		return distance;
	}
	
	
	/*
	public void changeTuple(int x, int y) {
		this.first=x;
		this.second=y;
	}
	*/

	public boolean tupleBelong(ArrayList<Tuple> tuples){
		for(Tuple t:tuples){
			if(this.equals(t)){
				return true;
			}
		}
		return false;
	}

	public boolean toClip(Tuple min, Tuple max){
		return (first<min.first) || (first>max.first) || (second<min.second) || (second>max.second);
	}

	public Tuple clip(Tuple min, Tuple max){
		
		if(first<min.getFirst()){
			first=min.getFirst();
		}

		else if(first>max.getFirst()){
			first=max.getFirst();
		}

		if(second<min.getSecond()){
			second = min.getSecond();
		}

		else if(second >max.getSecond()){
			second = max.getSecond();
		}
		return this;
	
	}

	@Override 
	public boolean equals(Object t){
		return first==((Tuple)t).first && second==((Tuple)t).second;
		
	}
	

	
}
