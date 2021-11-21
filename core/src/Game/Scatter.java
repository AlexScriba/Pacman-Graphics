package Game;

public class Scatter extends Mode {

	private static Scatter instance;

	public static Scatter getInstance() {
		return instance;
	}

	private Scatter(SearchAlgorithm sa){
		super(sa);
		
	}

	public static void setScatter(SearchAlgorithm sa){
		if(instance==null){
		instance = new Scatter(sa);
	}
		else{
			System.out.println("Scatter has already been intitialized");
		}
	}

	@Override
	public void addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "ScatterBottomLeftCorner":
			algorithms.add(new ScatterBottomLeftCorner());
			break;
		case "ScatterBottomRightCorner":
			algorithms.add(new ScatterBottomRightCorner());
			break;
		case "ScatterTopLeftCorner":
			algorithms.add(new ScatterTopLeftCorner());
			break;
		case "ScatterTopRightCorner":
			algorithms.add(new ScatterTopRightCorner());
			break;
		default:
			// throw error
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

	@Override
	public void deleteMode(){
		instance =null;
	}
}
