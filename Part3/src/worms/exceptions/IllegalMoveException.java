package worms.exceptions;

import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal moves. Each
 * illegal move exception involves the worm.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalMoveException extends RuntimeException {

	/**
	 * Initialize this new illegal move exception with given worm.
	 * 
	 * @param worm
	 *            The worm for this new illegal move exception.
	 * @post The worm for this new illegal move exception is equal to the
	 *       given worm. | new.getWorm() == worm
	 * @effect This new illegal move exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalMoveException(Worm worm) {
		this.worm = worm;
	}

	/**
	 * Return the worm of this illegal move exception.
	 */
	@Basic
	@Immutable
	public Worm getWorm() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal move exception.
	 */
	private final Worm worm;
}

