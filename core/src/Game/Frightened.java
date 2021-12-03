package Game;

public class Frightened extends Mode {

	private static Frightened instance;
	
	private Frightened(SearchAlgorithm sa) {
		super(sa);
		
	}

	public static Frightened getInstance() {
		return instance;
	}
	

	public static boolean setFrightened(SearchAlgorithm sa) {
		if (instance==null){
		instance = new Frightened(sa);
		return true;}
		else{
			return false;
		}
	}
	
	//no algorithm to add here
	@Override
	public boolean addAlgorithm(String algorithmName) {
		return false;
	}

	@Override
	public boolean resolveCollision() {
		return true;
		
	}

	@Override
	public String getModeName() {
		return "Frightened";
	}

	@Override
	public Mode deleteMode(){
		instance =null;
		return instance;
	}

}
