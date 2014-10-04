/**
 * 
 * Base class for storing mobile-to-tile collision information
 * @author Jacob Charles
 * 
 */

public class TileCollision {
	
	//the collision planes (no Z-axis here)
	private int _xAxis;
	private int _yAxis;
	
	private Mobile _m;
	private Tile _t;

	/**
	 * Generate a collision record between two Mobile objects
	 * 
	 * @param a the object recording collisions
	 * @param b the other object
	 */
	public TileCollision (Mobile m, Room r) {
		_m = m;
		/**
		 * find the destination tile
		 * compare edges, calculate x and y axis
		 */
		//TODO: replace with actual collision detection!
		_t = null;
		_xAxis = 0;
		_yAxis = 0;
	}
	
	/**
	 * @return the object of the collision
	 */
	public Mobile getObject() {
		return _m;
	}
	
	/**
	 * @return the tile of the collision
	 */
	public Tile getTile() {
		return _t;
	}
	
	/**
	 * @return the status of x-axis collisions (1 = right side, -1 = left side, 0 = no collision)
	 */
	public int getXAxis() {
		return _xAxis;
	}
	
	/**
	 * @return the status of y-axis collisions (1 = bottom side, -1 = top side, 0 = no collision)
	 */
	public int getYAxis() {
		return _yAxis;
	}
}
