package Game;

public class Scatter extends Mode {

	private static Scatter instance;

	public static Scatter getInstance() {
		return instance;
	}

	private Scatter(SearchAlgorithm sa){
		super(sa);
		
	}

	public static boolean setScatter(SearchAlgorithm sa){
		if(instance==null){
		instance = new Scatter(sa);
		return true;
	}
		else{
			return false;
		}
	}
	//void
	@Override
	public boolean addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "ScatterBottomLeftCorner":
			algorithms.add(new ScatterBottomLeftCorner());
			return true;
		case "ScatterBottomRightCorner":
			algorithms.add(new ScatterBottomRightCorner());
			return true;
		case "ScatterTopLeftCorner":
			algorithms.add(new ScatterTopLeftCorner());
			return true;
		case "ScatterTopRightCorner":
			algorithms.add(new ScatterTopRightCorner());
			return true;
		default:
			return false;
		}
	}

	
	/**
	 * This method checks if any ghost instance bumps into our Pacman. If so, decrease the Pacman's life value by 1.
	 * @apiNote Collision should be resolved only for one ghost in this mode.
	 * @param g the ghost instance to check for
	 * @param found ??
	 * @return void
	 * 
	 */
	@Override
	public boolean resolveCollision() {
			
			return false;


	}

	@Override
	public String getModeName() {
		return "Scatter";
	}

	//void
	@Override
	public Mode deleteMode(){
		instance =null;
		return instance;
	}
}
