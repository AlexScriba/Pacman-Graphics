package Game;

import java.util.ArrayList;
import java.util.Random;


public class RandomSearch implements SearchAlgorithm{
	public static RandomSearch instance;
	private Random random;
	private RandomSearch() {
		random = new Random();
	}
	
	public static RandomSearch getInstance() {
		if (instance == null) 
		{
			instance=new RandomSearch();
		}
		//delete afterwards
		return instance;
	}
	

	public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction>accessibleDirections) {
		Tuple start = points.get(0);
		int[] indeces = new int[4];
		Direction nextDirection = null;
		int k=0;
		for(int i=0;i<accessibleDirections.size();i++) {
			Direction dir=accessibleDirections.get(i); 
			Tuple candidate = start.sum(dir.DirectionToTuple());
			if(Maze.getInstance().getSymbol(candidate.getSecond(), candidate.getFirst())=='W') {
				continue;
			}
			if(!Maze.getInstance().isInHouse(start) && Maze.getInstance().isInHouse(candidate)){
				continue;
			}
			indeces[k++]=i;
			
		}
		if(k>0)
		nextDirection=accessibleDirections.get(indeces[random.nextInt(k)]);
		return nextDirection;
		
	}
	
	@Override
	public SearchAlgorithm destroySearch() {
		instance=null;
		return instance;
	}
}
