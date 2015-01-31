package game;
/**
 * 
 * Timer class for managing a loop and controlling framerate
 * @author Jacob Charles
 * 
 */
public class Timer {

	//length 
	private int _loopTime;
	private long _loopEnd;

	/**
	 * Create a stop watch with the designated loop length
	 * 
	 * @param length
	 * 		expected length of a loop in milliseconds
	 */
	public Timer(int length) {
		_loopTime = length;
	}

	/**
	 * Log start time of a loop
	 */
	public void startLoop() {
		_loopEnd = System.currentTimeMillis()+_loopTime;
	}

	/**
	 * Pause for a time in the middle of a loop
	 * 
	 * @param m
	 * 		number of milliseconds to pause
	 */
	public void pause(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rest at the end of a loop
	 */
	public void endLoop() {
		long tillEnd = _loopEnd-System.currentTimeMillis();
		if (tillEnd > 0) {
			pause(tillEnd);
		}
	}
}
