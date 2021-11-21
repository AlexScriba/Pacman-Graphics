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
		return instance;
	}
	
	
	public static void setA_star() {
		if(instance == null) {
			instance = new A_star(Maze.getInstance().getN()*Maze.getInstance().getM(), Maze.getInstance().getN());
			
		}
		
		else {
			System.out.println("A* has already been set!");
		}
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
	
	Node start_node=new Node(start, null,  Tuple.distance(start, end));
	frontier.add(start_node);
	nodes[Tuple.tuppleToInt(start, moduo)]=start_node;
	
	Node current=null;
	ArrayList<Tuple> path=new ArrayList<Tuple>();
	
	while(!frontier.isEmpty()) {
		current = frontier.poll();

		if((current.getID()).equals(end)) {
			break;
		}

		double prev_heuristic=Tuple.distance(current.getID(),end);
		for(Direction dir:possibleDirections) {
			Tuple child = current.getID().sum(dir.DirectionToTuple());
			if(maze.getSymbol(child.getSecond(), child.getFirst())=='W') {
				continue;
			}

			double child_value=current.getValue() - prev_heuristic + 1 + Tuple.distance(child,end);
			Node child_node=nodes[Tuple.tuppleToInt(child, moduo)];
			//Push the node in the queue if it has never been investigated
			if(child_node==null) {
				child_node = new Node(child, current, child_value);
				frontier.add(child_node);
				nodes[Tuple.tuppleToInt(child, moduo)]=child_node;
				/*
				if (nodes[child_node.getParent()]==null) {
					System.out.println("Greska!");
				}*/
			}
			//Update the node if it is the frontier
			else if(frontier.contains(child_node)) {
				frontier.remove(child_node);
				child_node.setValue(child_value);
				child_node.setParent(current);
				frontier.add(child_node);
			}
			
		}
		
		
	}
	
	
		Stack<Tuple> reverse = new Stack<>();
		
		while(current.getParent()!=null) {
			reverse.push(current.getID());
			current=current.getParent();
		}
		
		reverse.push(start);
		
		while(!reverse.isEmpty()) {
			path.add(reverse.pop());
		}

		for(int i=0;i<size;i++){
			nodes[i]=null;
		}
	
	return path;
}




public Tuple BFS(Tuple t, ArrayList<Direction> possibleDirections){
	Maze maze=Maze.getInstance();
	ArrayList<Tuple> tupleQueue = new ArrayList<>();
	ArrayList<Tuple> visited = new ArrayList<>();
	tupleQueue.add(t);
	while(!tupleQueue.isEmpty()){
		Tuple current = tupleQueue.remove(0);
		char indicator = maze.getSymbol(current.getSecond(), current.getFirst());
		if(indicator!='W' && indicator!='N'){
			return current;
		}
		for(Direction dir:possibleDirections){
			Tuple child = current.sum(dir.DirectionToTuple());
			if(!child.toClip(maze.getTopLeftCorner(), maze.getBottomRightCorner()) && !child.tupleBelong(visited) && !child.tupleBelong(tupleQueue)){
				tupleQueue.add(child);
			}
		}
		visited.add(current);
	}
		return null;

}


public Tuple findClosestGoal(Tuple t, ArrayList<Direction> possibleDirections){
	Maze maze = Maze.getInstance();
	t.clip(maze.getTopLeftCorner(), maze.getBottomRightCorner());
	char symbol = maze.getSymbol(t.getSecond(), t.getFirst());
	if(symbol =='W' || symbol=='N'){
		return BFS(t, possibleDirections);
	}
	return t;


}

@Override
public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> possibleDirections){
	Tuple start = points.get(0);
	Tuple end  = points.get(1);
	end = findClosestGoal(end, possibleDirections);
	ArrayList<Tuple> path = getPath(start, end, possibleDirections);
	Tuple nextTuple = null;
	if(path.size()>1){
	nextTuple = path.get(1);
	}

	else{
		nextTuple = start;
	} 

	for(Direction dir: possibleDirections){
		if(start.sum(dir.DirectionToTuple()).equals(nextTuple)){
			return dir;
		}
	}
	return null;
}

@Override
public void destroySearch() {
	instance=null;
}

}


