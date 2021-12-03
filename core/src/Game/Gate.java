package Game;

public class Gate extends GameObject{
    private Gate(int x_coordinate, int y_coordinate) {
		super(x_coordinate, y_coordinate, 'G');
	}
    
	private static Gate instance;

	
	public static boolean setGate(int x_coordinate, int y_coordinate){
		if(instance==null){
			instance = new Gate(x_coordinate, y_coordinate);
			return true;
		}
		else{
			return false;
		}
	}

	public static Gate getInstance(){
		return instance;
	}

	public static Gate deleteGate(){
		instance = null;
		return instance;
	}
}
