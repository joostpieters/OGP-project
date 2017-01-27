package worms.exceptions;

import worms.model.World;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal size of worlds. Each
 * illegal size exception involves the illegal width and/or height and the world.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalSizeException extends RuntimeException {

	/**
	 * Initialize this new illegal size exception with given width, height and given
	 * world.
	 * 
	 * @param width
	 *            The width for this new illegal size exception.
	 * @param height
	 *            The height for this new illegal size exception.
	 * @param world
	 * 			  The world for this new illegal size exception.
	 * @post The width for this new illegal size exception is equal to the
	 *       given width. | new.getWidth() == width
	 * @post The height for this new illegal size exception is equal to the
	 *       given height. | new.getHeight() == height
	 * @post The world for this new illegal size exception is the same
	 *       as the given world. | new.getWorld() == world
	 * @effect This new illegal size exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalSizeException(World world, double width, double height) {
		this.world = world;
		this.width = width;
		this.height = height;
	}

	/**
	 * Return the world of this illegal size exception.
	 */
	@Basic
	@Immutable
	public World getWorld() {
		return world;
	}

	/**
	 * Variable registering the world of this illegal size exception.
	 */
	private final World world;
	
	/**
	 * Return the width of this illegal size exception.
	 */
	@Basic
	@Immutable
	public double getWidth() {
		return width;
	}

	/**
	 * Variable registering the width of this illegal size exception.
	 */
	private final double width;
	
	/**
	 * Return the height of this illegal size exception.
	 */
	@Basic
	@Immutable
	public double getHeight() {
		return height;
	}

	/**
	 * Variable registering the height of this illegal size exception.
	 */
	private final double height;
}

