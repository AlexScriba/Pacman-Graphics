package Game;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Interface Mode can be regarded as State pattern because of the collison resolved and also as a Factory methdo
//as it assigns ghosts certain algorithms
public abstract class Mode {
	protected ArrayList<GhostAlgorithm> algorithms = new ArrayList<GhostAlgorithm>();
	
	private SearchAlgorithm sa;

	public abstract boolean addAlgorithm(String algorithmString);

	public abstract boolean resolveCollision();

	public abstract Mode deleteMode();
	/**
	 * Return the ArrayList of algorithms
	 * @return algorithms
	 */
	public ArrayList<GhostAlgorithm> getAlgorithms() {
		return algorithms;
	}
	
	
	public Mode(SearchAlgorithm sa) {
		this.sa=sa;
		
	}

	public abstract String getModeName();
	
	public static boolean initModes(String filepath) {
		try {
			File file = new File(filepath);
			Scanner scanner = new Scanner(file);
			int numModes = Integer.parseInt(scanner.next());
			int numGhosts = Integer.parseInt(scanner.next());
			scanner.nextLine();

			for (int i = 0; i < numModes; i++) {
				String mode = scanner.nextLine();
				String search = scanner.nextLine();
				Mode tempMode = null;
				SearchAlgorithm tempSearch = null;
				
				switch(search) {
					case "GreedySearch":
						tempSearch = Greedy_Search.getInstance();
						break;
					case "AStar":
						tempSearch = A_star.getInstance();
						break;
					default:
						tempSearch = RandomSearch.getInstance();
						break;
				}

				switch (mode) {
					case "Chase":
						Chase.setChase(tempSearch);
						tempMode = Chase.getInstance();
						ModeDestructor.getInstance().addMode(tempMode);
						break;
					case "Scatter":
						Scatter.setScatter(tempSearch);
						tempMode = Scatter.getInstance();
						ModeDestructor.getInstance().addMode(tempMode);
						break;
					case "Frightened":
						Frightened.setFrightened(tempSearch);
						tempMode = Frightened.getInstance();
						ModeDestructor.getInstance().addMode(tempMode);
						break;
				}
				
				//False in case of Frightened mode
				//True in case of other modes
				if(scanner.hasNext()) {
					for (int j = 0; j < numGhosts; j++) {
						tempMode.addAlgorithm(scanner.nextLine());
					}
				}
			}

			scanner.close();
			return true;

		} catch (Exception e) {
            System.out.printf("Algorithm file %s not found\n", filepath);
            return false;
		}
	}
	
	public SearchAlgorithm getSearchAlgorithm() {
		return sa;
	}
	
	//void
	public SearchAlgorithm destroySearch() {
		if(sa != null)
			sa.destroySearch();
		sa=null;
		return sa;
	}
		
	//void
	public ArrayList<GhostAlgorithm> destroyGA() {
		algorithms = null;
		return  algorithms;
	}
}
