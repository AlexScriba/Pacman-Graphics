package Game;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Interface Mode can be regarded as State pattern because of the collison resolved and also as a Factory methdo
//as it assigns ghosts certain algorithms
public abstract class Mode {
	protected ArrayList<GhostAlgorithm> algorithms = new ArrayList<GhostAlgorithm>();
	
	private SearchAlgorithm sa;

	public abstract void addAlgorithm(String algorithmString);

	public abstract boolean resolveCollision();

	public abstract void deleteMode();
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
	
	public static void initModes(String filepath) {
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
				SearchAlgorithm tempSearch=null;
				
				switch(search) {
				case "GreedySearch":
					tempSearch = Greedy_Search.getInstance();
					break;

				case "AStar":
				A_star.setA_star();
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
				default:
					// throw error
					break;
				}
				for (int j = 0; j < numGhosts; j++) {
					tempMode.addAlgorithm(scanner.nextLine());
				}
			}

			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		}
	
	public SearchAlgorithm getSearchAlgorithm() {
		return sa;
	}
		
	public void destroyAlgorithms() {
		algorithms = null;
		if(sa!=null) {
		sa.destroySearch();
		}
			
	}
}
