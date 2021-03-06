package Game;

//import java.io.File;
//import java.util.ArrayList;

public abstract class Difficulty {

	public abstract boolean doReverse();
	
	public abstract String getGhostFile();
	
	public abstract String getPacMan();
	
	public abstract String getAlgorithm();
	
	public abstract String getMaze();

	protected String pathPrefix = "/../resources/main/TextFiles/";
}
