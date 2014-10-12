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
	 * Generate a collision record between a Mobile object and the room
	 * 
	 * @param m the object recording collisions
	 * @param r the room being collided with
	 */
	public TileCollision (Mobile m, Tile t) {
		_m = m;
		_t = t;

		//x axis collisions
		if ( (m.getNextTop() > t.getBottom()) && (m.getNextBottom() < t.getTop()) ) { //y aligned
			//a moving left or b moving right
			if (m.getLeft() <= t.getRight() && m.getNextLeft() > t.getRight()) {
				_xAxis = -1;
			}
			//a moving right or b moving left
			if (m.getRight() >= t.getLeft() && m.getNextRight() < t.getLeft()) {
				_xAxis = 1;
			}
		}

		//y axis collisions
		if ( (m.getNextLeft() > t.getRight()) && (m.getNextRight() < t.getLeft()) ) {//x aligned
			//a moving up or b moving down
			if (m.getTop() <= t.getBottom() && m.getNextTop() > t.getBottom()) {
				_yAxis = -1;
			}
			//a moving down or b moving up
			if (m.getBottom() >= t.getTop() && m.getNextBottom() < t.getTop()) {
				_yAxis = 1;
			}
		}
	}

	/**
	 * @return true if a collision has occured, otherwise false
	 */
	public boolean getCollision() {
		return (_xAxis != 0)||(_yAxis != 0);
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
