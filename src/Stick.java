/**
 * 
 * Class for control "sticks" (directional input)
 *
 */

public class Stick {

	private int _x, y;

	/**
	 * 
	 * @return horizontal position of the stick
	 */
	public int get_x() {
		return _x;
	}

	/**
	 * 
	 * @param _x new horizontal position of stick
	 */
	public void set_x(int _x) {
		this._x = _x;
	}

	/**
	 * 
	 * @return vertical position of the stick
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y new vertical position of the stick
	 */
	public void setY(int y) {
		this.y = y;
	}
}
