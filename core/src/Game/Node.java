package Game;
/**
 * This Node class is an internal data structure used in A_star algorithm
 */

public class Node implements Comparable<Node>{
private Tuple id; //tuple to be used in the sequence
private double value; //used in the priority queue
private Node parent;

public Node(Tuple id, Node parent, double value) {
	this.id=id;
	setParent(parent);
	setValue(value);
}


public Tuple getID() {
	return id;
}

public double getValue() {
	return value;
}

public void setValue(double value) {
	this.value=value;
}

public Node getParent() {
	return parent;
}

public void setParent(Node parent) {
	this.parent=parent;
}

@Override
public int compareTo(Node other) {
	Double arg1 = this.getValue();
	Double arg2 = other.getValue();
	return arg1.compareTo(arg2);
	}


}
