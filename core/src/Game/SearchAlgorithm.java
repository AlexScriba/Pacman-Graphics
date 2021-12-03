package Game;

import java.util.ArrayList;

public interface SearchAlgorithm {
	public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> accebileDirections);
	public SearchAlgorithm destroySearch();
}
