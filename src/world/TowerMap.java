package world;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents a tower map and its connections, but does not actually contain floor objects
 * Includes generator method
 * 
 * @author Jacob Charles
 */

public class TowerMap {
	private TowerNode _nodes[];
	private int _height, _width, _depth;
	private int _bossX[];
	private int _bossY[];
	private int _startX;
	private int _startY;
	private int _powers;
	
	/**
	 * A constructor that generates a randomized tower map
	 * 
	 * @param seed: a seed value for the random function
	 * @param width: the number of zones wide each floor is
	 * @param height: the number of zones high each floor is
	 * @param depth: the number of floors in the tower
	 */
	public TowerMap(int seed, int width, int height, int depth) {
		//build a tower of specified height, all uninitialized
		_nodes = new TowerNode[width*height*depth];
		_width = width;
		_height = height;
		_depth = depth;
		//there will be depth-1 bosses
		_bossX = new int[depth-1];
		_bossY = new int[depth-1];
		//there will be depth-2 powers to gain
		_powers = depth-2;
		
		//TODO: Make this into a thing that uses that seed! Probably.
		Random rand = new Random();
		
		//Pick random start position on floor 0
		_startX = rand.nextInt(_width);
		_startY = rand.nextInt(_height);
		
		//TODO: Rest of the algorithm
	}
	
	/**
	 * Get all nodes, explored or otherwise, adjacent to node at a position
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return ArrayList of TowerNodes adjacent to the specified position 
	 */
	private ArrayList<TowerNode> _getNeighbors(int x, int y, int z) {
		ArrayList<TowerNode> neighbors = new ArrayList<TowerNode>();
		//TODO: find neighbors, don't go out of bounds (0 - wid, 0 - hei, 1 - dep-1)
		return neighbors;
	}
	
	/**
	 * Expand a node (from the frontier, typically)
	 * @param x
	 * @param y
	 * @param z
	 */
	private void _expand(int x, int y, int z) {
		//TODO: Node expansion!
	}
	
	/**
	 * Forcibly expand the node up, to ensue all floors are reached
	 * @param x
	 * @param y
	 * @param z
	 */
	private void _forceUp(int x, int y, int z) {
		//TODO: Force it to rise
	}
	
	/**
	 * Distribute bosses between the floors
	 */
	private void _addBosses() {
		//TODO: Place boss nodes
	}
	
	/**
	 * Apply locks to nodes based on boss positions
	 */
	private void _addLocks() {
		//TODO: Apply locks after bosses
	}
	
	/**
	 * @param x the x position within the floor
	 * @param y the y position within the floor
	 * @param z the floor number within the tower
	 * 
	 * @return the node at the specified position
	 */
	public TowerNode getNode(int x, int y, int z) {
		return _nodes[z*_width*_height + y*_width + x];
	}

	/**
	 * @return the width of each floor of the tower
	 */
	public int getWidth() {
		return _width;
	}
	
	/**
	 * @return the height of each floor of the tower
	 */
	public int getHeight() {
		return _height;
	}
	
	/**
	 * @return the number of floors in the tower
	 */
	public int getDepth() {
		return _depth;
	}
}
