package Game;

import java.util.ArrayList;

//Code smell: A* and Greedy Search should inherit the same class
// Class Game should have a Path finding algorithm as a field, as this can be dictated by the level difficulty
public class Greedy_Search implements SearchAlgorithm{
	private static Greedy_Search instance;
	private Greedy_Search() {
	}
	
	public static Greedy_Search getInstance() {
		if (instance == null) 
		{
			instance=new Greedy_Search();
		}
		return instance;
		
	}
	
	
	//sort by Manhattan distance
	
	
	
	
	@Override
	public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> accessibleDirections) {
		Tuple start = points.get(0);
		Tuple end = points.get(1);
		Maze m = Maze.getInstance();
		Direction nextDirection = null;
		//true for test 6
		if(start.equals(end)) {
			return nextDirection;
		}
		
		//go towards the gate
		//true for test 1
		//false for test2
		if(m.isInHouse(start)){
			end = Gate.getInstance().getTuple().sum(Direction.UP.DirectionToTuple());	
		}

		double min_distance = m.getM() + m.getN();
		
		
		//each test case is going to access the loop
		for(Direction dir: accessibleDirections) {
			
			Tuple candidate = start.sum(dir.DirectionToTuple());
			//false for test 1
			//TF for test 2
			if(Maze.getInstance().getSymbol(candidate.getSecond(), candidate.getFirst())=='W') {
				continue;
			}
			
			//test1: F- 
			//test2: TT, TF
			if(!m.isInHouse(start) && m.isInHouse(candidate)){
				continue;
			}
			
			
			//test1: TF
			//test2: TF
			if(min_distance > Tuple.distance(candidate, end)) {
				min_distance = Tuple.distance(candidate, end);
				nextDirection = dir;
			}
			
			
		}
		//test 3,4,5 -> used to achieve coverage in Tuple
		//test 3 achieves false in conditional in char getSymbol() in maze
		
		return nextDirection;
		
	}
	@Override
	public SearchAlgorithm destroySearch() {
		instance=null;
		return instance;
	}


}
