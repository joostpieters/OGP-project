package worms.exceptions;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal coordinates for a certain object. Each
 * illegal coordinate exception involves the illegal coordinate.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalCoordinateException extends RuntimeException {

	/**
	 * Initialize this new illegal number exception with given number and given
	 * bank account.
	 * 
	 * @param coordinate
	 *            The coordinate for this new illegal coordinate exception.
	 * @post The number for this new illegal number exception is equal to the
	 *       given number. | new.getNumber() == number
	 * @post The coordinate for this new illegal coordinate exception is the same
	 *       as the coordinate. | new.getCoordinate() == coordinate
	 */
	public IllegalCoordinateException(double coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Return the coordinate of this illegal coordinate exception.
	 */
	@Basic
	@Immutable
	public double getCoordinate() {
		return coordinate;
	}

	/**
	 * Variable registering the coordinate of this illegal coordinate exception.
	 */
	private final double coordinate;
}

