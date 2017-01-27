package worms.exceptions;

import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal mass. Each
 * illegal mass exception involves the illegal mass and the worm.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalMassException extends RuntimeException {

	/**
	 * Initialize this new illegal mass exception with given mass and given
	 * worm.
	 * 
	 * @param mass
	 *            The mass for this new illegal mass exception.
	 * @param account
	 *            The worm for this new illegal mass exception.
	 * @post The mass for this new illegal mass exception is equal to the
	 *       given mass. | new.getMass() == mass
	 * @post The worm for this new illegal mass exception is the same
	 *       as the given worm. | new.getWorm() == worm
	 * @effect This new illegal mass exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalMassException(final Worm worm, final double mass) {
		this.worm = worm;
		this.mass = mass;
	}

	/**
	 * Return the worm of this illegal mass exception.
	 */
	@Basic
	@Immutable
	public Worm getWorm() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal mass exception.
	 */
	private final Worm worm;
	
	/**
	 * Return the mass of this illegal mass exception.
	 */
	@Basic
	@Immutable
	public double getMass() {
		return mass;
	}

	/**
	 * Variable registering the mass of this illegal mass exception.
	 */
	private final double mass;
}

