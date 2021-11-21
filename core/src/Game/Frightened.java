package Game;

public class Frightened extends Mode {

	private static Frightened instance;
	
	private Frightened(SearchAlgorithm sa) {
		super(sa);
		
	}

	public static Frightened getInstance() {
		return instance;
	}
	
	public static void setFrightened(SearchAlgorithm sa) {
		if (instance==null){
		instance = new Frightened(sa);}
		else{
			System.out.println("Frightened has already been initialized");
		}
		
	}

	@Override
	public void addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "FrightenedWandering":
			algorithms.add(new FrightenedWandering());
			break;
		default:
			// throw error
		}
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
	public void deleteMode(){
		instance =null;
	}

}
