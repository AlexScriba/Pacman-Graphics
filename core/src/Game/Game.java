package Game;

import java.util.ArrayList;

//Game is used as the Facade for the Main class
//It will handle all changes related to the states of Pacman, Ghosts and the Maze
public class Game {
	private Mode mode;
	
	private Difficulty difficulty;

	private static Game instance = new Game();
	
	private int totalTicks = 0;
	private int totalModeChanges = 0;
	private int totalFrightenedTicks = 0;

	public static Game getInstance() {
		return instance;
	}

	private Game() {
		//Difficulty.setMazeArray();
	}

	public int gameInit(String difficultyStr, String mode) {
		boolean state = setDifficulty(difficultyStr);
		if(!state) {
			return 1; // Difficulty Init failed
		}
		state = Ghost.initGhosts(difficulty.getGhostFile());
		if(!state) {
			return 2; // Ghost Init failed
		}
		state = PacMan.initPacMan(difficulty.getPacMan());
		if(!state) {
			return 3; // Pacman Init failed
		}
		state = Maze.initMaze(difficulty.getMaze());
		if(!state) {
			return 4; // Maze Init failed
		}
		Mode.initModes(difficulty.getAlgorithm());
		
		setMode(mode);
		return 0; // Success
	}

	public void gameTick(String cmd) {
		//s.close(); solution to bug 8)
		Direction pacPotDir = null;
		switch (cmd) {
		case "Up":
			pacPotDir = Direction.UP;
			//pac.setDirection(Direction.UP);
			break;
		case "Down":
			//pac.setDirection(Direction.DOWN);
			pacPotDir = Direction.DOWN;
			break;
		case "Left":
			//pac.setDirection(Direction.LEFT);
			pacPotDir=Direction.LEFT;
			break;
		case "Right":
			//pac.setDirection(Direction.RIGHT);
			pacPotDir = Direction.RIGHT;
			break;
		case "Reset":
			resetGame();
			break;
		default:
			pacPotDir = PacMan.getInstance().getDirection();
			break;
		}
		
		if(pacPotDir!=null) {
			handleMovements(pacPotDir);
		}
		
		if(mode.getModeName().equals("Frightened")) {
			totalFrightenedTicks++;
			if(totalFrightenedTicks % 10 == 0) {
				setMode("Chase");
			}
		} else {
			totalTicks++;
			if(totalTicks % 10 == 0 && totalModeChanges < 7) {
				if(mode.getModeName().equals("Scatter"))
					setMode("Chase");
				else
					setMode("Scatter");
				totalModeChanges++;
			}
		}
	}


	public void setMode(String mode) {
		
		if (mode != null) {
			// if mode is changed, revere ghosts' direction
			if (this.mode != null && !this.mode.getModeName().equals(mode) && (
					mode.equals("Frightened") ||
					(mode.equals("Chase") && this.mode.getModeName().equals("Scatter")) ||
					(mode.equals("Scatter") && this.mode.getModeName().equals("Chase")) )) {
				Ghost.reverseDirectionsOfAllGhosts();
			}
			
			if (mode.equals("Chase"))
				this.mode = Chase.getInstance();
			else if (mode.equals("Scatter"))
				this.mode = Scatter.getInstance();
			else if (mode.equals("Frightened"))
				this.mode = Frightened.getInstance();
			else {
				// handle the exception in the testing phase

			}
			
			return;
		}
		// handle the exception in the testing phase
		
	}
	
	public boolean setDifficulty(String difficultyStr) {
		if(difficultyStr == null) {
			return false;
		} else if(difficultyStr.equals("Easy")) {
			this.difficulty = Easy.getInstance();
		} else if(difficultyStr.equals("Medium")) {
			this.difficulty = Medium.getInstance();
		} else if(difficultyStr.equals("Hard")) {
			this.difficulty = Hard.getInstance();
		} else {
			return false;
		}
		return true;
	}

	public void resetGame() {
		PacMan.getInstance().resetPacMan();
		Ghost.resetGhosts();
		Maze.getInstance().resetMaze();		
		/* Start debugging */
		System.out.println("Game is reset");
		/* End debugging */
	}

	
	public void handleMovements(Direction pacPotDir) {
		moveGhosts();
		char symbol = movePacMan(pacPotDir);
		int ghost_eaten_num = 1;
		ArrayList<Ghost> ghosts = Ghost.getGhosts();
		boolean pacmanNoLose=true;
		
		for(int i=0;i<ghosts.size();i++) {
			
			if(this.isCollision(ghosts.get(i))) {
				pacmanNoLose  = mode.resolveCollision();
				if(pacmanNoLose) {
					this.PacDefeatGhost(ghosts.get(i), ghost_eaten_num);
					ghost_eaten_num *= 2;
				}
				
				else {
					this.GhostDefeatPac();
					// No score given to Pacman
					break;
				}
			}
		}
			
		if(pacmanNoLose && symbol!='W') {
			if(symbol=='U') {
				setMode("Frightened");
				PacMan.getInstance().changeScore(50);
			}
			
			else if(symbol=='F') {
				PacMan.getInstance().incrementFood();
				PacMan.getInstance().changeScore(10);
			}
			Maze.getInstance().removeObject(PacMan.getInstance().getTuple());
			
		}
			
	}
	
		
	public boolean isCollision(Ghost g) {
		PacMan pac = PacMan.getInstance();
		Tuple pacPosition = pac.getTuple();
		Tuple ghostPosition = g.getTuple();
		Tuple pacDTuple = pac.getDirection().DirectionToTuple();
		Tuple ghostDTuple = g.getDirection().DirectionToTuple();
		boolean isTileSwap = pacPosition.sum(ghostDTuple).equals(ghostPosition) && ghostPosition.sum(pacDTuple).equals(pacPosition);
		boolean sameTile = pacPosition.equals(ghostPosition);
		return isTileSwap || sameTile;
		
	}
	
	
	
	
	
	public void moveGhosts() {
		ArrayList<Ghost> ghosts=Ghost.getGhosts();
		ArrayList<GhostAlgorithm> algs = mode.getAlgorithms();
		for(int i=0;i<ghosts.size();i++) {
			algs.get(i).behave(ghosts.get(i), mode.getSearchAlgorithm(), difficulty.doReverse());
		}
	}
	
	public char movePacMan(Direction pacPotDir) {
		PacMan pac = PacMan.getInstance();
		Tuple new_pac=pac.getTuple().sum((pacPotDir).DirectionToTuple());
		Maze maze = Maze.getInstance();
		char symbol = maze.getSymbol(new_pac.getSecond(), new_pac.getFirst());
		
		if(symbol !='W') {
			pac.setDirection(pacPotDir);
			pac.move();
		}
		
		return symbol;
		
	}
	
	
	
	public void gameOver() {
		//Score system should be called from here
		PacMan.destroyPacman();
		Ghost.destroyGhosts();
		ModeDestructor.getInstance().destroyAllModes();
		Maze.destroyMaze();
	
	}
	
	public boolean isGameOver() {
		return PacMan.getInstance().getLives()<1 || PacMan.getInstance().getFood()==Maze.getInstance().getTotalNumOfFood();
	}
	
	public void PacDefeatGhost(Ghost g, int ghost_eaten_num) {
		g.resetPosition();
		PacMan.getInstance().changeScore(200 * ghost_eaten_num);
	}
	
	public void GhostDefeatPac() {
		PacMan pac = PacMan.getInstance();

		pac.resetPosition();
		pac.changeLives(-1);
	}
	
	public void printMaze() {
		//Get pacman's position
		int pacmanX =  PacMan.getInstance().getTuple().getFirst();
		int pacmanY =  PacMan.getInstance().getTuple().getSecond();
	
		//Tell the positions of the 4 ghosts
		ArrayList<Ghost> ghosts = Ghost.getGhosts();
		
		Maze maze = Maze.getInstance();

		for(int i=0;i<maze.getM();i++) {
			for(int j=0;j<maze.getN();j++) {
			    
				// see if pacman is here
				if (i == pacmanY && j ==pacmanX) {
					System.out.print("P ");
				}
				else {
					// see if any of the four ghosts is here
					boolean isGhostHere = false;
					
					for (Ghost g: ghosts) {
						if (i == g.getTuple().getSecond() && j == g.getTuple().getFirst()) {
							
							/**
							 * Here is some quick fix. The Pink ghost's symbol is 'P', same as Pacman.
							 * So to make distinction I use letter 'I' to print the Pink ghost
							 */
							if (g.getSymbol() == 'P')	// only for Pink ghost						
								System.out.print("I"+" ");
							else						// for other ghosts - print normally
								System.out.print(g.getSymbol()+" ");
							
							isGhostHere = true;
							break;
						}
					}
					if (!isGhostHere) {
						// Neither Pacman nor ghost is here. We print maze 
						if (maze.getSymbol(i, j) == 'N') {
							System.out.print("  ");
						} else {
							System.out.print(maze.getSymbol(i, j)+" ");
						}
					}
				}
				
			}
			System.out.println();
		}
}
	
	public boolean isInFrightenedMode() {
		return mode.getModeName() == "Frightened";
	}
}
