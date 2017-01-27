package worms.exceptions;

import worms.model.World;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling game states. Each illegal game state exception 
 * involves the illegal world.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalGameStateException extends RuntimeException {

	/**
	 * Initialize this new illegal game state exception with given world.
	 * 
	 * @param world
	 *            The world for this new illegal game state exception.
	 * @post The world for this new game state exception is equal to the
	 *       given world. | new.getWorld() == world
	 * @effect This new illegal game state exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalGameStateException(World world) {
		this.world = world;
	}

	/**
	 * Return the world of this illegal game state exception.
	 */
	@Basic
	@Immutable
	public World getWorld() {
		return world;
	}

	/**
	 * Variable registering the world of this illegal game state exception.
	 */
	private final World world;
}

