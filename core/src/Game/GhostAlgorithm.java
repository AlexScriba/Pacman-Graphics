package Game;

/**
 * This is an interface that applies to all algorithm instances
 * Defines that each algorithm should implement a behave() method
 *
 */
public interface GhostAlgorithm {
	public void behave(Ghost g, SearchAlgorithm sa, boolean doReverse);
}
