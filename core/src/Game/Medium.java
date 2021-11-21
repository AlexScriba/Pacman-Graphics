package Game;

public class Medium extends Difficulty{
	private static Medium instance = new Medium();
	private Medium(){};
	
	public static Medium getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
		return pathPrefix + "mediumGhostData.txt";
	}
	
	@Override
	public String getPacMan() {
		return pathPrefix + "mediumPacMandata.txt";
	}
	
	@Override
	public String getAlgorithm() {
		return pathPrefix + "mediumAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
		return pathPrefix + "mediumMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return false;
	}
}
