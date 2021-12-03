package Game;

//import java.io.File;
//import java.util.ArrayList;

public abstract class Difficulty {

	//private static ArrayList<String> mazeFilePathname = new ArrayList<String>();
	//private static int index=3;
	
	public abstract boolean doReverse();
	
	public abstract String getGhostFile();
	
	public abstract String getPacMan();
	
	public abstract String getAlgorithm();
	
	public abstract String getMaze();
	
	protected String pathPrefix = "../core/assets/TextFiles/";
}
