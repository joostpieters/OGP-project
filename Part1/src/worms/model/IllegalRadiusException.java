package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal numbers for bank accounts. Each
 * illegal number exception involves the illegal number and the bank account.
 * 
 * @version 1.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalRadiusException extends RuntimeException {

	/**
	 * Initialize this new illegal number exception with given number and given
	 * bank account.
	 * 
	 * @param number
	 *            The number for this new illegal number exception.
	 * @param account
	 *            The bank account for this new illegal number exception.
	 * @post The number for this new illegal number exception is equal to the
	 *       given number. | new.getNumber() == number
	 * @post The bank account for this new illegal number exception is the same
	 *       as the given bank account. | new.getAccount() == account
	 * @effect This new illegal number exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalRadiusException(Worm worm, double radius) {
		this.worm = worm;
		this.radius = radius;
	}

	/**
	 * Return the worm of this illegal radius exception.
	 */
	@Basic
	@Immutable
	public Worm getWorm() {
		return worm;
	}

	/**
	 * Variable registering the worm of this illegal radius exception.
	 */
	private final Worm worm;
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	@Basic
	@Immutable
	public double getRadius() {
		return radius;
	}

	/**
	 * Variable registering the radius of this illegal radius exception.
	 */
	private final double radius;
}

