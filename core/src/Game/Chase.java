package Game;

public class Chase extends Mode {
	private static Chase instance;

	public static Chase getInstance() {
		return instance;
	}
	
	private Chase(SearchAlgorithm sa) {
		super(sa);
	}


	public static boolean setChase(SearchAlgorithm sa) {
		if(instance==null){
		instance = new Chase(sa);
		return true;
		}
		else{
			return false;
		}
		
	}
	
	
	@Override
	public boolean addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "ChaseAggressive":
			algorithms.add(new ChaseAggressive());
			return true;
		case "ChaseAmbush":
			algorithms.add(new ChaseAmbush());
			return true;
		case "ChasePatrol":
			algorithms.add(new ChasePatrol());
			return true;
		case "ChaseRandom":
			algorithms.add(new ChaseRandom());
			return true;
		default:
			return false;
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
	public Mode deleteMode(){
		instance =null;
		return instance;
	}

}
