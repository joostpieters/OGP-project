package worms.exceptions;

import worms.model.Projectile;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of exceptions signaling illegal yields. Each
 * illegal yield exception involves the illegal yield and the projectile.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 */
@SuppressWarnings("serial")
public class IllegalYieldException extends RuntimeException {

	/**
	 * Initialize this new illegal yield exception with given yield and given
	 * projectile.
	 * 
	 * @param yield
	 *            The yield for this new illegal yield exception.
	 * @param projectile
	 *            The projectile for this new illegal yield exception.
	 * @post The yield for this new illegal yield exception is equal to the
	 *       given yield. | new.getYield() == yield
	 * @post The projectile for this new illegal yield exception is the same
	 *       as the given projectile. | new.getProjectile() == projectile
	 * @effect This new illegal yield exception is further initialized as a new
	 *         runtime exception involving no diagnostic message and no cause. |
	 *         super()
	 */
	public IllegalYieldException(final Projectile projectile, final int yield) {
		this.projectile = projectile;
		this.yield = yield;
	}

	/**
	 * Return the projectile of this illegal yield exception.
	 */
	@Basic
	@Immutable
	public Projectile getProjectile() {
		return projectile;
	}

	/**
	 * Variable registering the projectile of this illegal yield exception.
	 */
	private final Projectile projectile;
	
	/**
	 * Return the yield of this illegal yield exception.
	 */
	@Basic
	@Immutable
	public int getYield() {
		return yield;
	}

	/**
	 * Variable registering the yield of this illegal yield exception.
	 */
	private final int yield;
}

