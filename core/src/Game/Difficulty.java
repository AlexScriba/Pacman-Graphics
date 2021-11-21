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
	/*
	public static void setMazeArray() {
		File directoryPath = new File("src/resource");
	    String contents[] = directoryPath.list();
	    for(String file:contents) {
	    	if(file.startsWith("map")) {
	    		mazeFilePathname.add(file);
	    	}
	    }
	}
	/*
	public String getMaze() {
		String fileName = "src/resource/"+mazeFilePathname.get(index);
		index=(index+1)%(mazeFilePathname.size());
		return fileName;
	}
	*/
}
