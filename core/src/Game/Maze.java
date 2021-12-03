package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	/**
	 * m: the dimension of x-coordinate
	 */
	private int m;
	/**
	 * n: the dimension of y-coordinate
	 */
	private int n;
	
	
	private GameObject[][] matrix;
	private final ArrayList<GameObject> resetObjectList = new ArrayList<GameObject>();
	private static Maze instance;
	private int totalFood = 0; 
	private Tuple minCornerHouse;
	private Tuple maxCornerHouse;
	

	/** Must add a zero-parameter non-private constructor here.
	 *  Otherwise the MazeStub class will not work.
	 */
	protected Maze(){
		this.m = 0;
		this.n = 0;
	}
	
	
	//read m and n from the file, as well as the maze structure
	public static boolean initMaze(String file) {
		if(instance!=null) {
			System.out.println("Maze has already been generated!");
			return true;
		} else {
			instance = new Maze();
			return instance.generateMaze(file);
		}
		
	}
	
	public boolean isInHouse(Tuple t){
		boolean isTupleInHouse = t.toClip(minCornerHouse, maxCornerHouse);
		return !isTupleInHouse;
	}

	
	public static Maze getInstance() {
		return instance;
	}
	
	public int getN() {
		return n;
	}
	
	public int getM() {
		return m;
	}
	
	/**
	 * return false if matrix[i][j] == null
	 * */
	public boolean removeObject(Tuple t) {
		boolean addedToResetList = false;
		int i=t.getSecond();
		int j=t.getFirst();
		if(matrix[i][j]!=null) {
			resetObjectList.add(matrix[i][j]);
			addedToResetList = true;
		}
		matrix[i][j]=null;
		return addedToResetList;
	}
	
	public char getSymbol(int i,int j) {
		if(matrix[i][j]==null) {
			return ' ';
		}
		else {
			return matrix[i][j].getSymbol();
		}
	}
	
	public static Maze destroyMaze() {
		instance = null;
		Gate.deleteGate();
		return instance;
		
	}
	
	public boolean resetMaze() {
		//use resetGameObject to reset the maze to its original position
		// Here resetObjectList will never be null, so I comment it out
//		if(resetObjectList == null)
//			return false;
		for(GameObject go : resetObjectList) {
			Tuple position = go.getTuple(); // Always a success
			int i = position.getSecond();
			int j= position.getFirst();
			matrix[i][j] = go;
		}
		resetObjectList.clear();
		return true;
	}
	
	public Tuple getBottomLeftCorner() {
		return new Tuple(0, m-1);
	}
	
	public Tuple getBottomRightCorner() {
		return new Tuple(n-1, m-1);
	}
	
	public Tuple getTopLeftCorner() {
		return new Tuple(0, 0);
	}
	
	public Tuple getTopRightCorner() {
		return new Tuple(n-1, 0);
	}
	
	/**
	 * return total number of food in maze
	 * when the game starts
	 * */
	public int getTotalNumOfFood() {
		return totalFood;
	}


	//Set the minCornerHouse and maxCornerHouse in this method
	public boolean generateMaze(String mapFile){
        // Scanner object to read from map file
        Scanner           fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        // Attempt to load the maze map file
        try {
            fileReader = new Scanner(new File(mapFile));
            String line = null;
            while (fileReader.hasNext()) {
//                try {
                line = fileReader.nextLine();
//                } catch (Exception eof) {
                    // throw new A5FatalException("Could not read resource");
//                    System.out.printf("Could not read the file %s further", mapFile);
//                    return false;
//                }

                if (line.trim().equals(""))
                    continue;    //skip dummy lines
                else
                    lineList.add(line);
            }

            int tileHeight = lineList.size();
            int tileWidth  = lineList.get(0).length();
            
            this.m=tileHeight;
            this.n=tileWidth;
            this.matrix=new GameObject[m][n];
            
            int gHouseCount = 0;

            for (int row = 0; row < tileHeight; row++) {
                String aLine = lineList.get(row);

                for (int column = 0; column < tileWidth; column++) {
                    char type = aLine.charAt(column);
                    /*Initially there could be only 4 kinds of tile: food(F), power-up(U), wall(W), and gate(G) */

                    if (type == 'F') {
                    	matrix[row][column] = new Food(column, row);
                    	totalFood++;
                    } else if (type == 'U')
                        matrix[row][column] = new PowerUp(column, row);
                    else if (type == 'W')
                        matrix[row][column] = new Wall(column, row);
                    else if (type == 'G'){
						Gate.setGate(column, row);
                        matrix[row][column] = Gate.getInstance();
					} else if (type == 'n')
						matrix[row][column] = new Tile(column, row);
					else if(type == 'C') { //Ghost House Corner
						if(gHouseCount == 0)
							minCornerHouse = new Tuple(column,row);
						else if(gHouseCount == 1)
							maxCornerHouse = new Tuple(column,row);
						matrix[row][column] = new Wall(column, row);
						gHouseCount++;
					}
                }
            }
            
            if(gHouseCount > 2)
            	System.out.println("The maze contains more than 1 Ghost House.\n Only the coordinates of the first Ghost House are kept");
            else if(gHouseCount < 2) {
            	System.out.println("The maze contains less than 1 Ghost House.\n Ghosts cannot start the game without their House");
            	// Throw an exception and close the game
            	return false;
            }
            
            return true;
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
            System.out.printf("Maze map file %s not found\n", mapFile);
            return false;
        }
    }
	
}
