package Game;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class A_star implements SearchAlgorithm{
	private static A_star instance;
	private Node[] nodes;
	private int moduo;
	private int size;
	
	
	private A_star(int size, int moduo) {
		this.moduo=moduo;
		this.nodes=new Node[size];
		this.size=size;
	}
	
	
	public static A_star getInstance() {
		if(instance == null) {
			instance = new A_star(Maze.getInstance().getN()*Maze.getInstance().getM(), Maze.getInstance().getN());
			
		}
		
		return instance;
	}
	
/** A_star.getPath() will return every tile that is on the solution path
 * 
 * @param start 
 *          start position in Tuple
 * @param end 
 *          end position in Tuple
 * @return <code>ArrayList<Tuple></code> 
 *          An ArrayList that contains every tile in our solution path 
 */
public ArrayList<Tuple> getPath(Tuple start, Tuple end, ArrayList<Direction> possibleDirections) {
	Maze maze=Maze.getInstance();
	PriorityQueue<Node> frontier = new PriorityQueue<>();
	
	Node current=new Node(start, null,  Tuple.distance(start, end));
	nodes[Tuple.tuppleToInt(start, moduo)]=current;
	ArrayList<Tuple> path=new ArrayList<Tuple>();
	
	//loop coverage 0 -> start = end (test 1)
	// loop coverage 1 -> start -> end (test 2)
	// loop coverage >1 -> start -u - v - ... -> end (test 3)
	
	while(!current.getID().equals(end)) {
		
		
		double prev_heuristic=Tuple.distance(current.getID(),end);
		//always going to go through 4 directions
		for(Direction dir:possibleDirections) {
			Tuple child = current.getID().sum(dir.DirectionToTuple());
			
			//false in  (test 2, test 3)
			//true in (test 2, test3)
			if(maze.getSymbol(child.getSecond(), child.getFirst())=='W') {
				continue;
			}

			double child_value=current.getValue() - prev_heuristic + 1 + Tuple.distance(child,end);
			Node child_node=nodes[Tuple.tuppleToInt(child, moduo)];
			
			//Push the node in the queue if it has never been investigated
			
			//it is false in (test 3)
			//it is true in test 2, test 3
			if(child_node==null) {
				child_node = new Node(child, current, child_value);
				frontier.add(child_node);
				nodes[Tuple.tuppleToInt(child, moduo)]=child_node;
			}
			
			
			//it is false in test 2, test3
			//it is true in test 3
			else if(frontier.contains(child_node)) {
				frontier.remove(child_node);
				child_node.setValue(child_value);
				child_node.setParent(current);
				frontier.add(child_node);
			}
			
		}
		current = frontier.poll();
		
		
	}
	
	
		Stack<Tuple> reverse = new Stack<>();
		
		//loop coverage: =0; start = end (test 1)
		//loop coverage: =1; start->end (test 2)
		//loop coverage >1; start -> u -> ... -> v -> end (test 3)
		while(current.getParent()!=null) {
			reverse.push(current.getID());
			current=current.getParent();
		}
		
		reverse.push(start);
		
		//loop coverage: never 0
		//loop coverage: 1 -> start = end (tests 1)
		//loop coverage: >1 -> start -> end (test 2,3)
		while(!reverse.isEmpty()) {
			path.add(reverse.pop());
		}

		//always >1 because of the Maze size => test (1,2,3)
		for(int i=0;i<size;i++){
			nodes[i]=null;
		}
	
	return path;
}
//test 3 included the coverage of Maze


//purpose: find a closest tile to the starting tile which is a wall or an empty tile to be a goal for
//ArrayList<Tuple> getPath(Tuple, Tuple, ArrayList<Direction>)
public Tuple BFS(Tuple t, ArrayList<Direction> possibleDirections){
	Maze maze=Maze.getInstance();
	ArrayList<Tuple> tupleQueue = new ArrayList<>();
	ArrayList<Tuple> visited = new ArrayList<>();
	char indicator = maze.getSymbol(t.getSecond(), t.getFirst());
	Tuple current = t;
	
	//loop coverage: 0: (test 4)
	//loop coverage 1:  (test 5)
	// loop coverage >1: (test 6), (test 7)
	//test4: FF
	//test5: T-, FF
	//test6: FF, T-, FT
	//test7: T-, FF (exists to "cover" Tuple coverage)
	
	
	while(indicator == 'W' || indicator=='n'){
		
		//all possible directions
		for(Direction dir:possibleDirections){
			Tuple child = current.sum(dir.DirectionToTuple());
			
			//test5:TTT
			//test6:TTT, F-, TF-, TTF
			//test7: TTT, F-, TF-, TTF
			if(!child.toClip(maze.getTopLeftCorner(), maze.getBottomRightCorner()) && !child.tupleBelong(visited) && !child.tupleBelong(tupleQueue)){
				tupleQueue.add(child);
			}
			}
		
		visited.add(current);
		current = tupleQueue.remove(0);
		indicator = maze.getSymbol(current.getSecond(), current.getFirst());
	}
	
		return current;

}


public Tuple findClosestGoal(Tuple t, ArrayList<Direction> possibleDirections){
	Maze maze = Maze.getInstance();
	t.clip(maze.getTopLeftCorner(), maze.getBottomRightCorner());
	return BFS(t, possibleDirections);
}

@Override
public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> possibleDirections){
	Tuple start = points.get(0);
	Tuple end  = points.get(1);
	end = findClosestGoal(end, possibleDirections);
	ArrayList<Tuple> path = getPath(start, end, possibleDirections);
	Tuple nextTuple = null;
	//T for test 11,12
	//F for test 13
	if(path.size()>1){
	nextTuple = path.get(1);
	}

	else{
		nextTuple = start;
	} 
	// TRAVERSE all possible directions until we find a direction that can lead to the specified tuple
	for(Direction dir: possibleDirections){
		//T test 11,12
		//F test 13
		if(start.sum(dir.DirectionToTuple()).equals(nextTuple)){
			return dir;
		}
	}
	return null;
}

@Override
public SearchAlgorithm destroySearch() {
	instance=null;
	return instance;
			
}

}


