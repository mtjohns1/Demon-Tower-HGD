/**
 * Helper class for normalizing x/y component vectors
 * 
 * @author Jacob Charles
 */
public class Direction {

	private double _x, _y;
	
	/**
	 * Take two components and create a vector in the same direction with a length of 1
	 * 
	 * @param x original x component
	 * @param y original y component
	 */
	public Direction(double x, double y) {
		double d = Math.sqrt(x*x+y*y);
		_x = x/d;
		_y = y/d;
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
}
