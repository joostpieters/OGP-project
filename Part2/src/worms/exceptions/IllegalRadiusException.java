package worms.exceptions;

import worms.model.BallisticObject;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal radiuses. Each
 * illegal radius exception involves the illegal radius and the ballistic object.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalRadiusException extends RuntimeException {

	/**
	 * Initialize this new illegal radius exception with given radius and given
	 * ballistic object.
	 * 
	 * @param radius
	 *            The radius for this new illegal radius exception.
	 * @param o
	 *            The ballistic object for this new illegal radius exception.
	 * @post The radius for this new illegal radius exception is equal to the
	 *       given radius. | new.getRadius() == radius
	 * @post The ballistic object for this new illegal radius exception is the same
	 *       as the given ballistic object. | new.getBallisticObject() == o
	 * @effect This new illegal radius exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalRadiusException(final BallisticObject o, final double radius) {
		this.ballisticObject = o;
		this.radius = radius;
	}

	/**
	 * Return the ballistic object of this illegal radius exception.
	 */
	@Basic
	@Immutable
	public BallisticObject getBallisticObject() {
		return ballisticObject;
	}

	/**
	 * Variable registering the ballistic object of this illegal radius exception.
	 */
	private final BallisticObject ballisticObject;
	
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

