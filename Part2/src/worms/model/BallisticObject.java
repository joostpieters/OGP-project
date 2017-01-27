package worms.model;

import worms.exceptions.IllegalJumpException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ballistic objects involving an activity state, 
 * 	vector and a world.
 * 
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public abstract class BallisticObject extends Entity {
	
	@Model
	protected BallisticObject(final World world, final Vector vector){
		this.isActive = true;
		this.vector = vector;
		this.world = world;
	}
	
	/**
	 * Returns whether or not this ballistic object is active.
	 * 	The is-active property of a ballistic object expresses whether or not
	 * 	the ballistic object is active in a game.
	 */
	public boolean isActive(){
		return this.isActive;
	}
	
	/**
	 * Set this ballistic object as inactive.
	 * 
	 * @post	The new is active state of this ballistic object
	 * 			is inactive.
	 * 			| new.isActive() == false
	 */
	public void setInActive(){
		this.isActive = false;
	}
	
	/**
	 * Variable registering whether or not a ballistic object is active.
	 */
	private boolean isActive;
	
	/**
	 * Returns the world in which this ballistic object is or was active.
	 * 	The world of a ballistic object expresses the world in which 
	 * 	the ballistic object is or was active.
	 */
	@Immutable
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Variable registering the world of an entity.
	 */
	private final World world;
	
	/**
	 * 
	 * @param	o
	 * 			The ballistic object which could be overlapping with this
	 * 			ballistic object.
	 * @param	currPos
	 * 			The position to be checked for the given ballistic object.
	 * @return	True if and only if the distance between this ballistic object
	 * 			and the given ballistic object is strict smaller than the 
	 * 			radius of this ballistic object plus the radius of the given 
	 * 			ballistic object.
	 * 			| result == getVector().getPosition().getDistanceTo(currPos) < 
	 * 			|	(getRadius() + o.getRadius())
	 */
	protected boolean overlapsWith(final BallisticObject o, 
			final Position currPos) {
		return getVector().getPosition().getDistanceTo(currPos) < 
				(getRadius() + o.getRadius());
	}
	
	/**
	 * Return the radius of this ballistic object.
	 * 	The radius of a ballistic object expresses the radius in meters from that ballistic object.
	 */
	@Basic
	@Raw
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Set the radius for this ballistic object to given radius.
	 * 
	 * @param	radius
	 * 			The new radius for this ballistic object.
	 * @post	The new radius for this ballistic object 
	 * 			is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Model
	@Raw
	protected void setRadius(final double radius){
		this.radius = radius;
	}
	
	/**
	 * Variable registering the radius of this ballistic object.
	 */
	private double radius;	
	
	/**
	 * Return the vector of this ballistic object.
	 *	The vector of a ballistic object determines where its vector 
	 *	is located and what its direction is.
	 */
	@Basic
	@Raw
	public Vector getVector() {
		return this.vector;
	}
	
	/**
	 * Set the vector for this ballistic object to given vector.
	 * 
	 * @param	radius
	 * 			The new radius for this ballistic object.
	 * @post	The new radius for this ballistic object is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Model
	@Raw
	protected void setVector(final Vector vector) {
		this.vector = vector;
	}
	
	/**
	 * Variable registering the vector of this ballistic object.
	 */
	private Vector vector;
	
	/**
	 * Calculates the position of a ballistic object at a given moment, during a jump.
	 * 
	 * @param	t
	 * 			The time for which the position will be returned.
	 * @return	The position that the ballistic object should be located 
	 * 			at on the given moment.
	 * 			| result[0] == getVector().getPosition().getX() +
	 *			|				getInitialJumpVelocity(getJumpForce()) *
	 *			|				Math.cos(getVector().getDirection()) * t
	 * 			| result[1] == getVector().getPosition().getY() + (
	 *			|				getInitialJumpVelocity(getJumpForce()) *
	 *			|				Math.sin(getVector().getDirection()) * t - 
	 *			|				0.5 * World.getSTD_ACCEL_EARTH() * Math.pow(t, 2))
	 */
	@Raw
	public double[] getJumpStep(final double t) {
		double[] jumpStep = new double[2];
		
		jumpStep[0] = getVector().getPosition().getX() +
				getInitialJumpVelocity(getJumpForce()) *
						Math.cos(getVector().getDirection()) * t;
		
		jumpStep[1] = getVector().getPosition().getY() + (
				getInitialJumpVelocity(getJumpForce()) *
						Math.sin(getVector().getDirection()) * t - 
							0.5 * World.getSTD_ACCEL_EARTH() * Math.pow(t, 2));
		return jumpStep;
	}
	
	/**
	 * Calculates the time that aa jump of this ballistic object in its current
	 * 	direction would take.
	 * 
	 * @return	The time a jump of this ballistic object would take.
	 * @throws	IllegalJumpException(this)
	 * 			The new ballistic object can not perform a jump.
	 * 			| !canJump()
	 */
	@Raw
	public double getJumpTime(final double timeStep) throws IllegalJumpException {
		if (!canJump())
			throw new IllegalJumpException(this);
		
		final Position currPos = getVector().getPosition();
		double[] jumpStep = new double[2];
		Position tmpPos = new Position(jumpStep[0], jumpStep[1]);
		double currentTime = getRadius() / 
				getInitialJumpVelocity(getJumpForce());
		do {
			jumpStep = getJumpStep(currentTime);
			currentTime += timeStep;
			tmpPos = new Position(jumpStep[0], jumpStep[1]);
		} while (!isJumpFinished(tmpPos));
		
		if (tmpPos.getDistanceTo(currPos) < getRadius() && this instanceof Worm)
			throw new IllegalJumpException(this);
		return currentTime;
	}
	
	/**
	 * Lets this ballistic object perform a jump.
	 * 
	 * @param	timeStep
	 * 			The accuracy of the time interval for which the jump 
	 * 			positions will be calculated.
	 */
	public void performJump(final double timeStep){
		double endPos[] = getJumpStep(getJumpTime(timeStep));
		getVector().getPosition().setX(endPos[0]);
		getVector().getPosition().setY(endPos[1]);
		if (!getWorld().liesWithinWorld(getVector().getPosition(), getRadius(), true))
				getWorld().terminate(this);
	}
	
	/**
	 * Lets the given ballistic object jump.
	 * 
	 * @param	timeStep
	 * 			The accuracy of the time interval with which the jump 
	 * 			positions will be calculated.
	 */
	public abstract void jump(final double timeStep);
	
	/**
	 * Checks wheter or not this ballistic object meets its requirements
	 * 	to jump.
	 */
	public abstract boolean canJump();
	
	/**
	 * Checkers whether or not a jump is in its ending state.
	 * 
	 * @param	currPos
	 * 			The current position of this ballistic object for which
	 * 			the invariants will be checked.
	 */
	public abstract boolean isJumpFinished(final Position currPos);
	
	/**
	 * Calculates and returns the initial jump velocity of this
	 * 	ballistic object.
	 * 
	 * @param	force
	 * 			The force with which the initial jump velocity will be
	 * 			calculated with.
	 */
	public abstract double getInitialJumpVelocity(final double force);
	
	/**
	 * Gets the jump force of this ballistic object.
	 */
	public abstract double getJumpForce();
}
