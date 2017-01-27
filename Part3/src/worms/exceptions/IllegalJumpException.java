package worms.exceptions;

import worms.model.BallisticObject;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal jumps. Each
 * illegal jump exception involves the illegal ballistic object.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalJumpException extends RuntimeException {

	/**
	 * Initialize this new illegal jump exception with given ballistic object.
	 * 
	 * @param o
	 *            The ballistic object for this new illegal jump exception.
	 * @post The ballistic object for this new illegal jump exception is equal to the
	 *       given ballistic object. | new.getNumber() == number
	 * @effect This new illegal jump exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalJumpException(BallisticObject o) {
		this.ballisticObject = o;
	}

	/**
	 * Return the ballistic object of this illegal jump exception.
	 */
	@Basic
	@Immutable
	public BallisticObject getBallisticObject() {
		return ballisticObject;
	}

	/**
	 * Variable registering the ballistic object of this illegal jump exception.
	 */
	private final BallisticObject ballisticObject;
}
