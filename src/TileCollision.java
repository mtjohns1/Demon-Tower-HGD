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

	private boolean _x, _y;
	
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
		_x = true;
		_y = true;
		_updateX();
		_updateY();
	}

	/**
	 * Generate a collision record between a Mobile object and the room along fixed axis
	 * 
	 * @param m the object recording collisions
	 * @param r the room being collided with
	 * @param x false ignores the x axis
	 * @param y false ignores the y axis
	 */
	public TileCollision (Mobile m, Tile t, boolean x, boolean y) {
		_m = m;
		_t = t;
		_x = x;
		_y = y;
		update();
	}

	/**
	 * Update the collisions for the current positions
	 */
	public void update()
	{
		if (_x) _updateX();
		if (_y) _updateY();
	}
	
	/**
	 * Update collisions horizontal between the two objects
	 */
	private void _updateX() {
		//x axis collisions
		if ( (_m.getTop() < _t.getBottom()) && (_m.getBottom() > _t.getTop()) ) { //y aligned
			//a moving left
			if (_m.getLeft() >= _t.getRight() && _m.getNextLeft() < _t.getRight()) {
				_xAxis = -1;
			}
			//a moving right
			if (_m.getRight() <= _t.getLeft() && _m.getNextRight() > _t.getLeft()) {
				_xAxis = 1;
			}
		}
	}
	
	/**
	 * Update collisions vertical between the two objects
	 */
	private void _updateY() {
		//y axis collisions
		if ( (_m.getLeft() < _t.getRight()) && (_m.getRight() > _t.getLeft()) ) {//x aligned
			//a moving up
			if (_m.getTop() >= _t.getBottom() && _m.getNextTop() < _t.getBottom()) {
				_yAxis = -1;
			}
			//a moving down
			if (_m.getBottom() <= _t.getTop() && _m.getNextBottom() > _t.getTop()) {
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
