/**
 * 
 * Base class for storing mobile-to-mobile collision information
 * @author Jacob Charles
 * 
 */

public class MobileCollision {

	//A collides with B's side at a point along an axis
	private int _xAxis;
	private int _yAxis;
	private int _zAxis;

	private Mobile _a, _b;
	
	/**
	 * Generate a collision record between two Mobile objects
	 * 
	 * @param a the object recording collisions
	 * @param b the other object
	 */
	public MobileCollision (Mobile a, Mobile b) {
		//record the two objects
		_a = a;
		_b = b;

		//x axis collisions
		if ( (a.getNextTop() < b.getNextBottom()) && (a.getNextBottom() > b.getNextTop()) //y aligned
				&& (a.getNextFront() < b.getNextBack()) && (a.getNextBack() > b.getNextFront()) ) { //z aligned
			//a moving left or b moving right
			if (a.getLeft() >= b.getRight() && a.getNextLeft() < b.getNextRight()) {
				_xAxis = -1;
			}
			//a moving right or b moving left
			if (a.getRight() <= b.getLeft() && a.getNextRight() > b.getNextLeft()) {
				_xAxis = 1;
			}
		}

		//y axis collisions
		if ( (a.getNextLeft() < b.getNextRight()) && (a.getNextRight() > b.getNextLeft()) //x aligned
				&& (a.getNextFront() < b.getNextBack()) && (a.getNextBack() > b.getNextFront()) ) { //z aligned
			//a moving up or b moving down
			if (a.getTop() >= b.getBottom() && a.getNextTop() < b.getNextBottom()) {
				_yAxis = -1;
			}
			//a moving down or b moving up
			if (a.getBottom() <= b.getTop() && a.getNextBottom() > b.getNextTop()) {
				_yAxis = 1;
			}
		}

		//z axis collisions
		if ( (a.getNextLeft() > b.getNextRight()) && (a.getNextRight() < b.getNextLeft()) //x aligned
				&& (a.getNextTop() > b.getNextBottom()) && (a.getNextBottom() < b.getNextTop()) ) { //y aligned
			//a moving out or b moving in
			if (a.getFront() <= b.getBack() && a.getNextFront() > b.getNextBottom()) {
				_zAxis = -1;
			}
			//a moving in or b moving out
			if (a.getBack() >= b.getFront() && a.getNextBack() < b.getNextFront()) {
				_zAxis = 1;
			}
		}
	}
	
	/**
	 * Make a  clone of a Collision object
	 * 
	 * @param c the Collision to clone
	 */
	public MobileCollision(MobileCollision c) {
		_a = c.getObjectA();
		_b = c.getObjectB();
		_xAxis = -c.getXAxis();
		_yAxis = -c.getXAxis();
		_zAxis = -c.getXAxis();
	}
	
	/**
	 * Creates a Collision recording the other perspective
	 * 
	 * Swaps A and B, as well as reversing directions
	 */
	private void _invert() {
		Mobile c = _a;
		_a = _b;
		_b = c;
		_xAxis = -_xAxis;
		_yAxis = -_yAxis;
		_zAxis = -_zAxis;
	}

	/**
	 * Creates a new Collision object with A and B swapped, along with the directions
	 * 
	 * @return an reversed copy
	 */
	public MobileCollision reverse() {
		MobileCollision mirror = new MobileCollision(this);
		mirror._invert();
		return mirror;
	}
	
	/**
	 * @return true if a collision has occured, otherwise false
	 */
	public boolean getCollision() {
		return (_xAxis != 0)||(_yAxis != 0);
	}
	
	/**
	 * @return the primary object of the collision
	 */
	public Mobile getObjectA() {
		return _a;
	}
	
	/**
	 * @return the other object
	 */
	public Mobile getObjectB() {
		return _b;
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
	
	/**
	 * @return the status of z-axis collisions (1 = bottom(z) side, -1 = top(z) side, 0 = no collision)
	 */
	public int getZAxis() {
		return _zAxis;
	}
}
