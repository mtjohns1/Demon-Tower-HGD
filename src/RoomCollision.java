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

		//set bounding box
		int l = _m.getLeft()/32;
		int t = _m.getTop()/32;
		int r = _m.getRight()/32;
		int b = _m.getBottom()/32;
		
		//"frontier" column and row
		int nx = _m.getX()/32, ny = _m.getY()/32;

		//determine "frontier" cells
		if (_m.getVx() > 0) {
			nx = r+1;
		}
		else if (_m.getVx() < 0) {
			nx = l-1;
		}
		if (_m.getVy() > 0) {
			ny = b+1;
		}
		else if (_m.getVy() < 0) {
			ny = t-1;
		}

		//find new horizontal cells
		for (Tile q : _r.getRange(nx-1, t-1, nx+1, b+1)) {
			_c.add(new TileCollision(_m, q, true, false));
		}

		//find new vertical cells
		for (Tile q : _r.getRange(l-1, ny-1, r+1, ny+1)) {
			_c.add(new TileCollision(_m, q, false, true));
		}
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
