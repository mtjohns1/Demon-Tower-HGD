import java.util.*;

/**
 * 
 * Base class for storing mobile-to-room (internal tiles) collision information
 * @author Jacob Charles
 * 
 */

public class RoomCollision {

	//the collisions
	private ArrayList<TileCollision> _c;

	//the participants
	private Mobile _m;
	private Room _r;

	private int _max(int a, int b) {
		if (a > b) return a;
		return b;
	}

	private int _min(int a, int b) {
		if (a < b) return a;
		return b;
	}

	/**
	 * Generate a collision record between a Mobile object and its Home room
	 * 
	 * @param m the object recording collisions
	 */
	public RoomCollision (Mobile m) {
		_m = m;
		_r = m.getHome(); //calculate the room from the object's home
		_c = new ArrayList<TileCollision>(); //array to store the impending collisions

		int l = _min(_m.getLeft(), _m.getNextLeft())/32;
		int t = _min(_m.getTop(), _m.getNextTop())/32;
		int r = _max(_m.getRight(), _m.getNextRight())/32;
		int b = _max(_m.getBottom(), _m.getNextBottom())/32;

		for (Tile q : _r.getRange(l, t, r, b)) {
			_c.add(new TileCollision(_m, q));
		}
		//System.out.println("Number of tiles hit: " + _c.size());
	}

	/**
	 * Remove collisions from the list that do not contain an actual collision
	 */
	public void hitsOnly() {
		for (int i = 0; i < _c.size(); i++) {
			if (_c.get(i).getCollision() == false) {
				_c.remove(i);
				i--;
			}
		}
	}

	/**
	 * @return the object of the collision
	 */
	public Mobile getObject() {
		return _m;
	}

	/**
	 * @return the room of the collision
	 */
	public Room getRoom() {
		return _r;
	}

	/**
	 * @return the list of resultant collisions
	 */
	public ArrayList<TileCollision> getCollisions() {
		return _c;
	}
}
