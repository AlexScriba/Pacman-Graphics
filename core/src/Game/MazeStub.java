package Game;

public class MazeStub extends Maze{
    /* By convention the size of the PacMan game board is 31 * 28 */
    public static final int mazeWidth = 28;
    public static final int mazeHeight = 31; 

    /* Singleton */
    private static MazeStub mazeInstance = new MazeStub();

    /* Keeps an internal GameObject 2D array */
    private GameObject[][] matrix;

    private MazeStub(){
        super();
        // initialize the game board
        matrix = new GameObject[MazeStub.mazeHeight][MazeStub.mazeWidth];
    }

    
    public static MazeStub getInstance(){
        return mazeInstance;
    }

    
    public static void setMaze(int m, int n){
        System.out.println("Error: Cannot set the dimension of maze. The maze dimension in class MazeStub is pre-set.");
    }

    @Override
    public int getN() {
		return MazeStub.mazeWidth;
	}
	@Override
	public int getM() {
		return MazeStub.mazeHeight;
	}
    @Override
    public void removeObject(Tuple t) {
    	int i = t.getSecond();
    	int j = t.getFirst();
		matrix[i][j]=null;
	}

//    @Override
//    public void generateMaze(String file){
//        super.generateMaze("map/map1.txt");
//    }

    /**
     * Creates a GameObject 2D array based on the map file
     * @param mapFile {String} the file path to a map file
     */
    

    public int getTotalNumOfFood() {
        int countFood = 0;
        for (int row=0; row<MazeStub.mazeHeight; row++){
            for (int col=0; col<MazeStub.mazeWidth; col++){
                if (matrix[row][col] != null){
                    if (matrix[row][col].getSymbol() == 'F'){
                        countFood++;
                    }
                }
            }
        }
        return countFood;
    }

    public char getSymbol(int i,int j) {
		//symbol for null is N
        if (matrix[i][j] == null)
            return 'n';
        else
		    return matrix[i][j].getSymbol();
	}

    /**
     * Calls the generateMaze() method in the parent class "Maze"
     */
    //public void genParentMaze(){
    //    super.generateMaze1();
   // }

    @Override
    /**
     * Return a string representation of maze. (Use symbols to denote GameObject by default)
     */
    public String toString(){
        String result = "";
        for (int row = 0; row < MazeStub.mazeHeight; row++){
            for (int col = 0; col < MazeStub.mazeWidth; col++){
                if (matrix[row][col] == null)
                    result = result + "n ";
                else
                    result = result + matrix[row][col].getSymbol() + " ";
            }
            result += "\n";
        }
        return result;
    }

}
/*
     * Reads from the map file and create the two dimensional array

    private void createCellArray(String mapFile) {

        // Scanner object to read from map file
        Scanner           fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        // Attempt to load the maze map file
        try {
            fileReader = new Scanner(new File(mapFile));

            while (true) {
                String line = null;

                try {
                    line = fileReader.nextLine();
                } catch (Exception eof) {

                    // throw new A5FatalException("Could not read resource");
                }

                if (line == null) {
                    break;
                }

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth  = lineList.get(0).length();

            // Create the matrix
            matrix = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++) {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++) {
                    char type = line.charAt(column);

                    matrix[row][column] = new Cell(column, row, type);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Maze map file not found");
        }
    }
    */