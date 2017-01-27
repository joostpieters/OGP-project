package worms.exceptions;

import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal names. Each
 * illegal name exception involves the illegal name and the worm.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalNameException extends RuntimeException {

	/**
	 * Initialize this new illegal name exception with given name and given
	 * worm.
	 * 
	 * @param name
	 *            The name for this new illegal name exception.
	 * @param worm
	 *            The worm for this new illegal name exception.
	 * @post The name for this new illegal name exception is equal to the
	 *       given name. | new.getName() == name
	 * @post The worm for this new illegal name exception is the same
	 *       as the given worm. | new.getWorm() == worm
	 * @effect This new illegal name exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalNameException(Worm worm, String name) {
		this.worm = worm;
		this.name = name;
	}

	/**
	 * Return the worm of this illegal name exception.
	 */
	@Basic
	@Immutable
	public Worm getWorm() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal name exception.
	 */
	private final Worm worm;
	
	/**
	 * Return the name of this illegal name exception.
	 */
	@Basic
	@Immutable
	public String getName() {
		return name;
	}

	/**
	 * Variable registering the name of this illegal name exception.
	 */
	private final String name;
}

