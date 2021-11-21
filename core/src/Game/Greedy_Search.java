package Game;

import java.util.ArrayList;

//Code smell: A* and Greedy Search should inherit the same class
// Class Game should have a Pathfinding algorithm as a field, as this can be dictated by the level difficulty
public class Greedy_Search implements SearchAlgorithm{
	private static Greedy_Search instance=new Greedy_Search();
	private Greedy_Search() {
	}
	
	public static Greedy_Search getInstance() {
		return instance;
	}
	
	
	//sort by Manhattan distance
	
	
	
	
	@Override
	public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> accessibleDirections) {
		Tuple start = points.get(0);
		Tuple end = points.get(1);
		Maze m = Maze.getInstance();
		//go towards the gate
		if(m.isInHouse(start)){
			end = Gate.getInstance().getTuple().sum(Direction.UP.DirectionToTuple());	
		}

		double min_distance = m.getM() + m.getN();
		Direction nextDirection = null;
		
		for(Direction dir: accessibleDirections) {
			
			Tuple candidate = start.sum(dir.DirectionToTuple());
			if(Maze.getInstance().getSymbol(candidate.getSecond(), candidate.getFirst())=='W') {
				continue;
			}

			if(!m.isInHouse(start) && m.isInHouse(candidate)){
				continue;
			}
			
			
			
			if(min_distance > Tuple.distance(candidate, end)) {
				min_distance = Tuple.distance(candidate, end);
				nextDirection = dir;
			}
			
			
		}
		
		return nextDirection;
		
	}
	@Override
	public void destroySearch() {
		instance=null;
	}


}
