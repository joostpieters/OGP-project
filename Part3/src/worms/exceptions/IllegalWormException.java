package worms.exceptions;

import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal numbers for bank accounts. Each
 * illegal number exception involves the illegal number and the bank account.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalWormException extends RuntimeException {

	/**
	 * Initialize this new illegal worm exception with given nworm.
	 * 
	 * @param worm
	 *            The worm for this new illegal worm exception.
	 * @post The worm for this new illegal worm exception is equal to the
	 *       given worm. | new.getWorm() == worm
	 * @effect This new illegal worm exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalWormException(Worm worm) {
		this.worm = worm;
	}

	/**
	 * Return the worm of this illegal worm exception.
	 */
	@Basic
	@Immutable
	public Worm getWorld() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal worm exception.
	 */
	private final Worm worm;
}

