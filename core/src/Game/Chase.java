package Game;

public class Chase extends Mode {
	private static Chase instance;

	public static Chase getInstance() {
		return instance;
	}
	
	private Chase(SearchAlgorithm sa) {
		super(sa);
	}

	public static void setChase(SearchAlgorithm sa) {
		if(instance==null){
		instance = new Chase(sa);
		}
		else{
			System.out.println("Chase has already been set");
		}
	}
	
	
	
	
	
	
	@Override
	public void addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "ChaseAggressive":
			algorithms.add(new ChaseAggressive());
			break;
		case "ChaseAmbush":
			algorithms.add(new ChaseAmbush());
			break;
		case "ChasePatrol":
			algorithms.add(new ChasePatrol());
			break;
		case "ChaseRandom":
			algorithms.add(new ChaseRandom());
			break;
		default:
			// throw error
		}
	}

	
	/**
	 * This method checks if any ghost instance bumps into our Pacman. If so, decrease the Pacman's life value by 1.
	 * @param g the ghost instance to check for
	 * @param found it is false when there is a collision
	 * @return void
	 */
	@Override
	public boolean resolveCollision() {
		return false;
	}

	@Override
	public String getModeName() {
		return "Chase";
	}

	@Override
	public void deleteMode(){
		instance =null;
	}

}
