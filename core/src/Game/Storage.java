package Game;

public class Storage {
    boolean visited;
    boolean frontier;


    public Storage(boolean visited, boolean frontier){
        this.visited=visited;
        this.frontier=frontier;
    }

    public boolean getFrontier(){
        return frontier;
    }

    public boolean getVisited(){
        return visited;
    }
}
