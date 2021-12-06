package Game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;

public class Easy extends Difficulty{
	private static Easy instance = new Easy();
	private Easy() {
		super();
		pathPrefix = System.getProperty("user.dir")  + pathPrefix;
	};
	
	public static Easy getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
//		return "src/Resource/easyGhostData.txt";
		
		return pathPrefix + "easyGhostData.txt";
//		return Gdx.files.internal(pathPrefix + "easyGhostData.txt");
	}
	
	@Override
	public String getPacMan() {
//		return "src/Resource/easyPacmanData.txt";
		return pathPrefix + "easyPacmanData.txt";
	}
	
	@Override
	public String getAlgorithm() {
//		return "src/Resource/easyAlgorithmData.txt";
		return pathPrefix + "easyAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
//		return "src/Resource/easyMaze.txt";
		return pathPrefix + "easyMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return false;
	}

}

