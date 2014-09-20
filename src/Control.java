/**
 * 
 * A class for the control input
 *
 */

public class Control {

	private Stick _move;
	private Stick _shoot;
	
	/**
	 * @return the stick that controls movement
	 */
	public Stick get_move() {
		return _move;
	}
	/**
	 * @param _move
	 * 			the new movement stick
	 */
	public void set_move(Stick _move) {
		this._move = _move;
	}
	/**
	 * @return the stick that controls firing
	 */
	public Stick get_shoot() {
		return _shoot;
	}
	/**
	 * @param _shoot
	 * 			the new firing stick
	 */
	public void set_shoot(Stick _shoot) {
		this._shoot = _shoot;
	}
}
