package worms.exceptions;

import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal shots. Each
 * illegal shot exception involves the worm.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalShootException extends RuntimeException {

	/**
	 * Initialize this new illegal shot exception with given worm.
	 * 
	 * @param worm
	 *            The worm for this new illegal shot exception.
	 * @post The worm for this new illegal shot exception is equal to the
	 *       given worm. | new.getWorm() == worm
	 * @effect This new illegal shot exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalShootException(final Worm worm) {
		this.worm = worm;
	}

	/**
	 * Return the worm of this illegal shot exception.
	 */
	@Basic
	@Immutable
	public Worm getWormt() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal shot exception.
	 */
	private final Worm worm;
}
