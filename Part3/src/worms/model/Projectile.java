package worms.model;

import worms.exceptions.IllegalJumpException;
import worms.exceptions.IllegalYieldException;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of projectiles involving a weapon, yield and owner.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 * 
 */
public class Projectile extends BallisticObject {

	/**
	 * Initialize this new projectile with given world, owner and weapon.
	 *
	 * @param	world
	 * 			The world for this new projectile.
	 * @param	owner
	 * 			The owner for this new projectile.
	 * @param	yield
	 * 			The yield for this new projectile.
	 * @param	weapon
	 * 			The weapon for this new projectile.
	 * @post	The new world of this new projectile is equal to the given world. 
	 *     		| new.getWorld() == world
	 * @post	The new vector of this new projectile is partly equal to that of 
	 * 			the owner of this projectile. The direction will be the same, whereas the
	 * 			position will be at a distance equal to the radius of the owner in its direction.
	 * 			| new.getVector() == getUpdatedVector(owner)
	 * @post	The new owner of this new projectile is equal to the given owner.
	 * 			| new.getOwner() == owner
	 * @post	The yield of this new projectile is equal to the given yield.
	 * 			| new.getYield() == yield
	 * @post	The new weapon of this this new projectile is equal to the given weapon.
	 * 			| new.getWeapon() == weapon
	 * @post	The radius of this new projectile is equal to the outcome of a fixed formula
	 * 			for the radius of this projectile.
	 * 			| new.getRadius() == calculateRadius()
	 * @throws	IllegalYieldException(this, yield)
	 * 			The new projectile can not have the given yield as its yield.
	 * 			| !isValidYield(yield)
	 */
	@Raw
	public Projectile(World world, Worm owner, int yield, Weapon weapon) 
			throws IllegalYieldException {
		super(world, getUpdatedVector(owner));
		this.owner = owner;
		if (!isValidYield(yield))
			throw new IllegalYieldException(this, yield);
		this.yield = yield;
		this.weapon = weapon;
		setRadius(calculateRadius());
	}

	/**
	 * Calculates the jump force of a projectile with a formula intended for it.
	 * 
	 * @return	The outcome off a fixed formula. 
	 * 			| result == weapon.getForce(yield)
	 */
	@Raw
	@Override
	public double getJumpForce() {
		return this.weapon.getForce(this.yield);
	}
	
	/**
	 * Calculates and returns the initial jump velocity of this
	 * 	projectile.
	 * 
	 * @param	force
	 * 			The force with which the initial jump velocity will be
	 * 			calculated with.
	 * @return	The given force divided by this mass of this projectile's weapon,
	 * 			multiplied with 1/2.
	 * 			| result == (force / this.weapon.getMass()) * 0.5
	 */
	@Override
	public double getInitialJumpVelocity(double force) {
		return (force / this.weapon.getMass()) * 0.5;
	}
	
	/**
	 * Calculates and returns the correct vector for this projectile.
	 * 
	 * @param	owner
	 * 			The owner of this projectile, its properties are needed to generate
	 * 			the new vector.	 * 
	 * @return	The updated vector.
	 * 			| result == new Vector(
	 *			|			new Position(
	 *			|			owner.getVector().getPosition().getX() + 
	 *			|				Math.cos(owner.getVector().getDirection()) * owner.getRadius(),
	 *			|			owner.getVector().getPosition().getY() + 
	 *			|				Math.sin(owner.getVector().getDirection()) * owner.getRadius()),
	 *			|			owner.getVector().getDirection())
	 */
	public static Vector getUpdatedVector(Worm owner){
		return new Vector(
				new Position(
				owner.getVector().getPosition().getX() + 
					Math.cos(owner.getVector().getDirection()) * owner.getRadius(),
				owner.getVector().getPosition().getY() + 
					Math.sin(owner.getVector().getDirection()) * owner.getRadius()),
				owner.getVector().getDirection());
	}
	
	/**
	 * Checks wheter or not this projectile can jump.
	 * 
	 * @return	True if and only if the owner of this projectile is active and if this projectile
	 * 			is active and if in the world of this projectile, the active projectile is set
	 * 			to this projectile. Otherwise false.
	 * 			| result == this.owner.isActive() && 
	 * 			|				isActive() && 
	 * 			|					getWorld().getActiveProjectile() == this
	 */
	public boolean canJump(){
		return this.owner.isActive() && isActive() && getWorld().getActiveProjectile() == this;
	}
	
	/**
	 * Checks whether or not the owner of this projectile can shoot.
	 * 
	 * @return	True if and only if the owner of this weapon can shoot.
	 * 			| result == this.owner.canShoot(this)
	 */
	public boolean ownerCanShoot() {
		return this.owner.canShoot();
	}
	
	/**
	 * Lets this projectile jump.
	 * 
	 * @param	timeStep
	 * 			The accuracy of the time interval for which the jump 
	 * 			positions will be calculated.
	 * @post	The new position of this projectile will be equal to the
	 * 			the outcome of the method intended for it.
	 * 			| new.getVector().getPosition().equals(
	 * 			| 	new Position(getJumpStep(getJumpTime(timeStep))[0],
	 * 			|		getJumpStep(getJumpTime(timeStep))[1]))
	 * @post	If and only if a worm is hit during the jump of this projectile,
	 * 			the hit points of that worm will be deducted with the damage of the weapon
	 * 			of this projectile.
	 * 			| new.wormHit.getHitPoints() == 
	 * 			|	old.wormHit.getHitPoints() - this.weapon.getDamage() 
	 * @post	The new is-active field of this projectile will be set to false.
	 * 			| new.isActive() == false
	 * @post	The active projectile of the world of this projectile will be set to null.
	 * 			| new.getWorld().getActiveProjectile() == null
	 * @throws	IllegalJumpException(this)
	 * 			This projectile can't jump.
	 * 			| !canJump()
	 */
	public void jump(double timeStep) {
		if (!canJump())
			throw new IllegalJumpException(this);
	
		performJump(timeStep);
		if (getWorld().hasOverlappingWorm(this, this.getVector().getPosition())){
			final Worm wormHit = getWorld().getOverlappingWorms(
					this, this.getVector().getPosition());
			if (wormHit != null)
				wormHit.deductHitPoints(weapon.getDamage());
		}
		setInActive();
		getWorld().setActiveProjectile(null);
	}
	
	/**
	 * Returns the weapon of this projectile.
	 * 	The weapon of this projectile expresses which weapon shot this projectile.
	 */
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	/**
	 * Variable registering the weapon of this projectile.
	 */
	private final Weapon weapon;
	
	/**
	 * Checks whether or not this jump is finished.
	 * 
	 * @param	currPos
	 * 			The temporary position for this projectile to check if the jump is in a
	 * 			finished state.
	 * @return	True if and only if this projectile with the given position doesn't lie
	 * 			completely in its world or if its located on an impassable spot or if it
	 * 			overlaps any worms in its world.
	 * 			| result == !getWorld().liesWithinWorld(currPos, getRadius(), true) ||
	 *			|			getWorld().isImpassableSpot(currPos, getRadius()) || !isActive() ||
	 *			|			getWorld().hasOverlappingWorm(this, currPos)
	 */
	public boolean isJumpFinished(Position currPos){ 
		return !getWorld().liesWithinWorld(currPos, getRadius()) ||
					getWorld().isImpassableSpot(currPos, getRadius()) || !isActive() ||
						getWorld().hasOverlappingWorm(this, currPos);
	}
	
	/**
	 * Returns the yield of this projectile.
	 * The yield expresses how much a certain value can reach.
	 */
	public int getYield() {
		return this.yield;
	}
	
	/**
	 * Checks whether or not this projectile has a valid yield.
	 * 
	 * @return	True if and only if this projectile has a valid yield.
	 * 			| result == isValidYield(this.yield)
	 */
	public boolean hasValidYield(){
		return isValidYield(this.yield);
	}
	
	/**
	 * Checks whether or not the given yield is valid.
	 * 
	 * @return	True if and only if the given yield is greater than or equal to the minimum valid
	 * 			yield and if the given yield is less than or equal to the maximum valid yield.
	 * 			| result == yield >= MIN_YIELD && yield <= MAX_YIELD
	 */
	public static boolean isValidYield(int yield){
		return yield >= MIN_YIELD && yield <= MAX_YIELD;
	}
	
	/**
	 * Variable registering the yield of this projectile.
	 */
	private int yield;

	/**
	 * Variable registering the minimum valid yield for all projectiles.
	 */
	private final static int MIN_YIELD = 0;
	
	/**
	 * Variable registering the maximum valid yield for all projectiles.
	 */
	private final static int MAX_YIELD = 100;
	
	/**
	 * Calculate and return the radius of this projectile dependant on its mass and density.
	 * 	The mathematic formula for the radius of a sphere will be used.
	 * 
	 * @return	The valid radius for this projectile.
	 * 			| result == Math.cbrt(3 * (weapon.getMass() / DENSITY)
	 *			|				/ (4 * Math.PI))
	 */
	private double calculateRadius(){
		return Math.cbrt(3 * (weapon.getMass() / DENSITY)
				/ (4 * Math.PI));
	}
	
	/**
	 * Returns the owner of this projectile.
	 * 	The owner of this worm expresses which worm shot the weapon of this projectile.
	 */
	public Worm getOwner() {
		return this.owner;
	}
	
	/**
	 * Variable registering the owner of this worm.
	 */
	private final Worm owner;
	
	/**
	 * Variable registering the density (kg/m³) for all ballistic objects.
	 */
	private final static double DENSITY = 7800;
}
