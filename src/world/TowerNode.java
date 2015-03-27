package world;
/**
 * A class that represents a node within a TowerMap. Does not detail the actual layout of rooms inside the Node 
 * 
 * @author Jacob Charles
 */

public class TowerNode {
	private boolean _lock[];
	private boolean _boss = false;
	private boolean _path[];
	private boolean _explored = false;
	private boolean _frontier = false;
	private int _floorType = -1;
	
	//assign directions to the node's connections
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	public static final int UP = 4;
	public static final int DOWN = 5;
	
	/**
	 * constructor produces an unvisited, unconnected, empty node
	 */
	public TowerNode() {
		//TODO: Make for arbitrary numbers of locks
		_lock = new boolean[4];
		for (int i = 0; i < 4; i++) _lock[i] = false;
		_path = new boolean[6];
		for (int i = 0; i < 6; i++) _path[i] = false;
	}
	
	/**
	 * @param i: the index of the lock to check
	 * @return true if it is locked
	 */
	boolean isLocked(int i) {
		return _lock[i];
	}
	/**
	 * @param i: the index of the connection to check
	 * @return true if it is connected
	 */
	boolean isConnected(int i) {
		return _path[i];
	}
	/**
	 * @return true if there is a boss
	 */
	boolean isBoss() {
		return _boss;
	}
	/**
	 * @return true if the node is explored
	 */
	boolean isExplored() {
		return _explored;
	}
	/**
	 * @return true if the node is in the frontier
	 */
	boolean isFrontier() {
		return _frontier;
	}
	/**
	 * @return the style of the floor the node is on
	 */
	int getType() {
		return _floorType;
	}
	
	/**
	 * @param i the index of the lock to set
	 */
	void lock(int i) {
		_lock[i] = true;
	}
	/**
	 * @param i the index of the connection to make
	 */
	void connect(int i) {
		_path[i] = true;
	}
	/**
	 * Place a boss here
	 */
	void setBoss() {
		_boss = true;
	}
	/**
	 * Mark the node as explored
	 */
	void explore() {
		_explored = true;
		_frontier = false;
	}
	/**
	 * Mark the node as frontier
	 */
	void setFrontier() {
		_frontier = true;
	}
	/**
	 * @param type
	 */
	void setType(int type) {
		_floorType = type;
	}
}
