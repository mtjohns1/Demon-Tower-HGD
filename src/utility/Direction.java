package utility;
/**
 * Helper class for normalizing x/y component vectors
 * 
 * @author Jacob Charles
 */
public class Direction {

	private double _x, _y, _d;
	
	/**
	 * Take two components and create a vector in the same direction with a length of 1
	 * 
	 * @param x original x component
	 * @param y original y component
	 */
	public Direction(double x, double y) {
		_d = Math.sqrt(x*x+y*y);
		_x = x;
		_y = y;
		if (_d == 0) return; //avoid divide-by-zero case!
		_x /= _d;
		_y /= _d;
	}
	
	/**
	 * @return the x component of the direction (range from -1 to +1)
	 */
	public double getX() {
		return _x;
	}

	/**
	 * @return the y component of the direction (range from -1 to +1)
	 */
	public double getY() {
		return _y;
	}
	
	/**
	 * @return the original length of the vector, pre-normalization
	 */
	public double getLength() {
		return _d;
	}
}
