package Game;

import java.util.ArrayList;

interface SearchAlgorithm {
	public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> accebileDirections);
	public void destroySearch();
}
