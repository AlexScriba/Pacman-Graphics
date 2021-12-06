package Game;

public class Hard extends Difficulty{
	private static Hard instance = new Hard();
	private Hard() {
		super();
		pathPrefix = System.getProperty("user.dir") + pathPrefix;
	};
	
	public static Hard getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
		return pathPrefix + "hardGhostData.txt";
	}
	
	@Override
	public String getPacMan() {
		return pathPrefix + "hardPacMandata.txt";
	}
	
	@Override
	public String getAlgorithm() {
		return pathPrefix + "hardAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
		return pathPrefix + "hardMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return true;
	}
}
