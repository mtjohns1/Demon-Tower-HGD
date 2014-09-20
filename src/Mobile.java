/**
 * 
 * Interface for mobile objects
 *
 */

public class Mobile {

	private int _x, _y, _z; //position in 3D space
	private int _vx, _vy, _vz; //3D velocities
	private int _w, _h, _d; //dimensions
	private Room _home; //room the object is in
	
	/**
	 * @return the room the object lives in
	 */
	public Room getHome() {
		return _home;
	}
	/**
	 * @param _home set the room the object lives in
	 */
	public void setHome(Room _home) {
		this._home = _home;
	}
	
	/**
	 * @return the x coordinate
	 */
	public int get_x() {
		return _x;
	}
	/**
	 * @param _x the new x coordinate
	 */
	public void set_x(int _x) {
		this._x = _x;
	}
	/**
	 * @return the y coordinate
	 */
	public int get_y() {
		return _y;
	}
	/**
	 * @param _y the new y coordinate
	 */
	public void set_y(int _y) {
		this._y = _y;
	}
	/**
	 * @return the z coordinate
	 */
	public int get_z() {
		return _z;
	}
	/**
	 * @param _z the new z coordinate
	 */
	public void set_z(int _z) {
		this._z = _z;
	}
	//get the x velocity
	public int get_vx() {
		return _vx;
	}
	//set the y velocity
	public void set_vx(int _vx) {
		this._vx = _vx;
	}
	//get the y velocity
	public int get_vy() {
		return _vy;
	}
	//set the y velocity
	public void set_vy(int _vy) {
		this._vy = _vy;
	}
	//get the z velocity
	public int get_vz() {
		return _vz;
	}
	//set the z velocity
	public void set_vz(int _vz) {
		this._vz = _vz;
	}
	//get the width of the object
	public int get_w() {
		return _w;
	}
	//set the width of the object
	public void set_w(int _w) {
		this._w = _w;
	}
	//get height of the object
	public int get_h() {
		return _h;
	}
	//set height of the object
	public void set_h(int _h) {
		this._h = _h;
	}
	//get the depth of the object
	public int get_d() {
		return _d;
	}
	//set the depth of the object
	public void set_d(int _d) {
		this._d = _d;
	}
}
