package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of worms involving a vector, radius, mass, action points, maximum action points,
 * a minimum radius and a name.
 * 
 * @invar Each worm can have its radius as its radius. 
 * 		| canHaveAsRadius(getRadius())
 * @invar Each worm can have its mass as its mass. 
 * 		| canHaveAsMass(getMass())
 * @invar Each worm can have its action points as its action points.
 * 		| canHaveAsActionPoints(getActionPoints())
 * @invar Each worm can have its maximum action points as its maximum action points.
 * 		| canHaveAsMaxActionPoints(getMaxActionPoints())
 * @invar Each worm can have its name as its name.
 * 		| isValidName(getName())
 * @invar The minimum size that applies to all worms must be a valid size.
 * 		| isValidMinRadius(getMinRadius())
 * 
 * @version 1.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */

public class Worm {
	
	/**
	 * Initialize this new worm with given vector, radius, mass, action points, 
	 * maximum action points and a name.
	 *
	 * @param	vector
	 * 			The vector for this new worm.
	 * @param	radius
	 * 			The radius for this new worm.
	 * @param	actionPoints
	 * 			The action points for this new worm.
	 * @param	name
	 * 			The name for this new worm.
	 * @post	The new vector of this new worm is equal to the given vector. 
	 *     		| new.getVector() == vector
	 * @post	The new radius of this new worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new name of this new worm is equal to the given name.
	 * 			| new.getName() == name
	 * @post	The mass of this new worm is equal to the outcome of the formula
	 * 			intended for it.
	 * 			| new.getMass() == calculateMass(radius)
	 * @post	The new action points of this this new worm is equal to the given
	 * 			action points.
	 * 			| new.getActionPoints() == actionPoints
	 * @post	The maximum action points of this new worm is equal to the mass
	 * 			of this worm rounded to the nearest integer and then casted to an
	 * 			integer type.
	 * 			| new.getMaximumActionPoints() == (int) round(getMass())
	 * @throws	IllegalRadiusException(this, radius)
	 * 			The new worm can not have the given radius as its radius.
	 * 			| !this.canHaveAsRadius(radius)
	 * @throws	IllegalNameException(this, name)
	 * 			The new worm can not have the given name as its name.
	 * 			| !isValidName(name)
	 */
	@Raw
	public Worm (Vector vector, double radius, String name) 
			throws IllegalNameException, IllegalRadiusException{
		this.vector = vector;
		this.MIN_RADIUS = PREDEFINED_MIN_RADIUS1;
		if (!this.canHaveAsRadius(radius))
			throw new IllegalRadiusException(this, radius);
		this.radius = radius;
		this.mass = calculateMass(radius);
		this.setMaxActionPoints(calculateMaxActionPoints(this.getMass()));
		this.setActionPoints(this.getMaxActionPoints());
		if (!isValidName(name))
			throw new IllegalNameException(this, name);
		this.name = name;
	}
	
	/**
	 * Variable registering the minimum radius (in meters) that applies to all worms.
	 */
	private static final double PREDEFINED_MIN_RADIUS1 = 0.25;
	
	/**
	 * Variable registering the density (kg/m³) that applies to all worms.
	 */
	private static final double DENSITY = 1062.0;
	
	/**
	 * Return the minimum radius of this worm.
	 * 	The minimum radius of a worm determines at least how big radius of
	 * 	the worm should be.
	 */
	@Basic
	@Immutable
	public double getMinRadius(){
		return this.MIN_RADIUS;
	}
	
	/**
	 * Check whether the given minimum radius is a valid minimum radius.
	 * 
	 * @param	MIN_RADIUS
	 * 			The minimum radius to check.
	 * @return	True if and only if the given minimum radius is strict positive.
	 * 			| result == (MIN_RADIUS > 0)
	 */
	public static boolean isValidMinRadius(double MIN_RADIUS){
		return MIN_RADIUS > 0;
	}
	/**
	 * Variable registering the minimum radius (expressed in meters) off this worm
	 */
	private final double MIN_RADIUS;
	
	/**
	 * Return the vector of this worm.
	 *	 The vector of a worm determines where the vector is located and what its
	 *	 direction is.
	 */
	@Basic
	@Raw
	public Vector getVector() {
		return this.vector;
	}
	
	/**
	 * Variable registering the vector of this worm.
	 */
	private Vector vector;
	
	/**
	 * Return the radius of this worm.
	 * 	The radius of a worm expresses the radius in meters from that worm.
	 */
	@Basic
	@Raw
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Set the radius for this worm to given radius.
	 * 
	 * @param	radius
	 * 			The new radius for this worm.
	 * @post	The new radius for this worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Model
	@Raw
	private void setRadius(double radius){
		this.radius = radius;
	}
	
	/**
	 * Check whether the given radius is a possible radius for the given worm.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is not below the minimum
	 * 			radius that applies to the given worm.
	 * 			| result == (radius >= this.getMinRadius())
	 */
	@Raw
	public boolean canHaveAsRadius(double radius){
		return radius >= this.getMinRadius();
	}
	
	/**
	 * Changes the radius for this worm and also changes the mass and maximum
	 * action points for this worm which are dependant on the given radius.
	 * 
	 * @param	radius
	 * 			The new radius for this worm.
	 * @post	The new radius for this worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new mass for this worm is equal to the outcome of the formula
	 * 			intended for it, which is invoked in the setMass(radius) method.
	 * 			| new.getMass() == calculateMass(radius)
	 * @post	The maximum action points of this new worm is equal to the mass
	 * 			of the current worm, rounded to the nearest integer and casted to an
	 * 			integer type.
	 * 			| new.getMaxActionPoints() == calculateMaxActionPoints(getMass())
	 * @throws	IllegalRadiusException(this, radius)
	 * 			This worm can not have the given radius as its radius.
	 * 			| !this.canAcceptForChangeRadius(radius)
	 */
	@Raw
	public void changeRadius(double radius) throws IllegalRadiusException{
		if (!this.canAcceptForChangeRadius(radius))
			throw new IllegalRadiusException(this, radius);
		this.setRadius(radius);
		this.setMass(radius);
		this.setMaxActionPoints(calculateMaxActionPoints(this.getMass()));
	}
	
	/**
	 * Check whether the radius of this worm can be changed to the given radius.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if this worm can legally change its action points due
	 * 			to the change of the radius and if this worm can have the given radius
	 * 			as its radius.
	 * 			| result == canHaveAsMaxActionPoints(
	 *			|				Worm.calculateMaxActionPoints(
	 *			|					Worm.calculateMass(radius))) 
	 *			|						&& canHaveAsRadius(radius)
	 */
	@Raw
	public boolean canAcceptForChangeRadius(double radius) {
		return this.canHaveAsMaxActionPoints(
				Worm.calculateMaxActionPoints(
						Worm.calculateMass(radius))) 
							&& this.canHaveAsRadius(radius);
	}
	
	/**
	 * Variable registering the radius of this worm.
	 */
	private double radius;
	
	/**
	 * Set the mass for this worm to the outcome of the formula intended for it
	 * which depends on the radius of the worm.
	 * 
	 * @param	mass
	 * 			The new mass for this worm.
	 * @post	The new mass for this worm is equal to the outcome of the formula
	 * 			intended for it.
	 * 			| new.getMass() == calculateMass(radius)
	 */
	@Model
	@Raw
	private void setMass(double radius){
		this.mass = calculateMass(radius);
	}
	
	/**
	 * Return the mass of this worm.
	 * 	The mass of a worm expresses the mass in kilograms from that worm.
	 */
	@Basic
	@Raw
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Calculates the mass for a worm with a formula which is dependant 
	 * on the radius of the worm.
	 * 
	 * @param	radius
	 * 			The radius of a worm which will be used in the formula.
	 * @return	The outcome off a fixed formula which is dependant on 
	 * 			the given radius.
	 * 			| result == (DENSITY * ((4.0 / 3) * Math.PI * 
	 * 			|	Math.pow(radius, 3)))
	 */
	public static double calculateMass(double radius){
		return DENSITY * ((4.0 / 3) * Math.PI * Math.pow(radius, 3));
	}
	
	/**
	 * Check whether the given radius is a possible radius for the given 
	 * worm.
	 * 
	 * @param	mass
	 * 			The mass to check.
	 * @return	True if and only if the given mass is equal to the outcome 
	 * 			of the formula intended to calculate the mass.
	 * 			| result == (mass == calculateMass(this.getRadius()))
	 */
	@Raw
	public boolean canHaveAsMass(double mass){
		return mass == calculateMass(this.getRadius());
	}
	
	/**
	 * Variable registering the mass of this worm.
	 */
	private double mass;
	
	/**
	 * Check whether this worm can have the given maximum action points
	 * as its maximum action points.
	 * 
	 * @param	maxActionPoints
	 * 			The maximum action points to check.
	 * @return	True if and only if the given maximum action points is 
	 * 			strict positive, and if the given maximum action points 
	 * 			are bigger or equal to the current action points of this 
	 * 			worm.
	 * 			| result == (maxActionPoints >= 0) &&
	 * 			|	(maxActionPoints >= this.actionPoints)
	 */
	@Raw
	public boolean canHaveAsMaxActionPoints(int maxActionPoints){
		return (maxActionPoints >= 0) && 
					(maxActionPoints >= this.actionPoints);
	}
	
	/**
	 * Set the maximum action points for this worm to the given maximum
	 * action points.
	 * 
	 * @param	maxActionPoints
	 * 			The new maximum action points for this worm.
	 * @post	If the worm can have the given maximum action
	 * 			points as its action points, the new maximum 
	 * 			action points for this worm is equal to the 
	 * 			given maximum action points.
	 * 			| new.getMaxActionPoints() == maxActionPoints
	 */
	@Raw
	@Model
	private void setMaxActionPoints(int maxActionPoints){
		if (this.canHaveAsMaxActionPoints(maxActionPoints))
			this.maxActionPoints = maxActionPoints;
	}
	
	/**
	 * Returns the maximum action points of this worm.
	 * 	The maximum action points of worm expresses the limit of its 
	 * 	possible action points.
	 */
	@Basic
	@Raw
	public int getMaxActionPoints(){
		return this.maxActionPoints;
	}
	
	/**
	 * Calculates the maximum action points for a worm by rounding the 
	 * mass of the worm to the nearest integer and casting it to an 
	 * integer type.
	 * 
	 * @param	mass
	 * 			The mass which will be used to calculate the maximum
	 * 			action points.
	 * @return	The given mass rounded to the nearest integer and then 
	 * 			casted to an integer.
	 * 			| result == (int) Math.round(mass)
	 */
	public static int calculateMaxActionPoints(double mass){
		return (int) Math.round(mass);
	}
	
	/**
	 * Variable registering the maximum action points of this worm.
	 */
	private int maxActionPoints;
	
	/**
	 * Use the given amount of action points of this worm.
	 * 
	 * @param	amount
	 * 			The amount of action points to be used.
	 * @post	If this worm can have the current action points 
	 * 			decremented by the given amount as its action points. 
	 * 			The new action points of this worm will be equal to 
	 * 			the old action points of this worm decremented with the 
	 * 			given amount of action points.
	 */
	@Raw
	public void useActionPoints(int amount){
		if (this.canHaveAsActionPoints(this.getActionPoints() - amount) &&
				amount >= 0)
			this.setActionPoints(this.getActionPoints() - amount);
		
	}
	
	/**
	 * Set the action points for this worm to given action points.
	 * 
	 * @param	actionPoints
	 * 			The new action points for this worm.
	 * @post	The new action points for this worm is equal to the given action
	 * 			points.
	 * 			| new.getActionPoints() == actionPoints
	 */
	@Model
	@Raw
	private void setActionPoints(int actionPoints){
		this.actionPoints = actionPoints;
	}
	
	/**
	 * Returns the action points of this worm.
	 * 	The action points of a worm expresses the current amount of usable
	 * 	action points.
	 */
	@Basic
	@Raw
	public int getActionPoints(){
		return this.actionPoints;
	}
	
	/**
	 * Check whether this worm can have the given action points
	 * as its action points.
	 * 
	 * @param	actionPoints
	 * 			The action points to check.
	 * @return	True if and only if the given amount of action points is
	 * 			positive, and if the given action points are less or
	 * 			equal to the maximum action points of this worm.
	 * 			| result == (actionPoints >= 0) &&
	 * 			|	(actionPoints <= this.maxActionPoints)
	 */
	@Raw
	public boolean canHaveAsActionPoints(int actionPoints){
		return (actionPoints >= 0) && (actionPoints <= this.maxActionPoints);
	}
	
	/**
	 * Variable registering the action points of this worm.
	 */
	private int actionPoints;
	
	/**
	 * Returns the name of this worm.
	 * 	The name of a worm expresses which worm is which to the end-user.
	 */
	@Basic
	@Raw
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name for this worm to the given name.
	 * 
	 * @param	name
	 * 			The new name for this worm.
	 * @post	The new name for this worm is equal to the given name.
	 * 			| new.getName() == name
	 */
	@Raw
	public void setName(String name){
		if (!isValidName(name))
			throw new IllegalNameException(this, name);
		this.name = name;
	}
	
	/**
	 * Check whether this worm can have the given name as its name.
	 * 
	 * @param	name
	 * 			The name to check.
	 * @return	True if and only if the given name has a length greater or equal to
	 * 			2, if the first character is uppercase, and if the given name uses no
	 * 			other character/symbols than those provided.
	 * 			| result == (name.length() >= 2) &&
	 *			|			(Character.isUpperCase(name.charAt(0))) &&
	 *			|				name.matches("[a-zA-Z'\" ]*")
	 */
	public static boolean isValidName(String name){
		return (name.length() >= 2) &&
							(Character.isUpperCase(name.charAt(0))) &&
								name.matches("[a-zA-Z'\" ]*");
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	/**
	 * Calculates the jump force of a worm with a formula intended for it.
	 * 
	 * @return	The outcome off a fixed formula.
	 * 			| result == (5 * this.getActionPoints()) +
	 *			|	(this.getMass() * STD_ACCEL_EARTH)
	 */
	@Raw
	public double calculateJumpForce() {		
		return (5.0 * this.getActionPoints()) +
				(this.getMass() * STD_ACCEL_EARTH);
	}
	
	/**
	 * Check whether this worm can perform a jump.
	 * 
	 * @return	True if and only if the worm is not facing downwards.
	 * 			| if (getVector().getDirection() < 0)
	 *			| result == (getActionPoints() > 0 && 
	 *			|	getVector().getDirection() % (2 * Math.PI) <= -Math.PI &&
	 *			|		getVector().getDirection() % (2 * Math.PI) >= 
	 *			|			(-2 * Math.PI))
	 *			| else
	 *			| 	result == (getActionPoints() > 0 && 
	 *			|		getVector().getDirection() % (2 * Math.PI) <= Math.PI &&
	 *			|			getVector().getDirection() % (2 * Math.PI) >= 0)
	 */
	@Raw
	public boolean canJump(){
		if (this.getVector().getDirection() < 0.0)
			return this.getActionPoints() > 0.0 && 
					this.getVector().getDirection() % (2.0 * Math.PI) <= -Math.PI &&
						this.getVector().getDirection() % (2.0 * Math.PI) >= (-2.0 * Math.PI);
		else
			return this.getActionPoints() > 0 && 
					this.getVector().getDirection() % (2.0 * Math.PI) <= Math.PI &&
						this.getVector().getDirection() % (2.0 * Math.PI) >= 0.0;
	}
	
	/**
	 * Calculates the initial jump velocity of a worm with a formula intended for it.
	 * 
	 * @param	force
	 * 			The force that will be used to calculate the initial jump velocity.
	 * @return	The outcome off a fixed formula.
	 * 			| result == ((force / getMass()) * 0.5)
	 */
	@Raw
	public double calculateInitialJumpVelocity(double force){
		return (force / this.getMass()) * 0.5;
	}
	
	/**
	 * Calculates the jump distance of a worm with a formula intended for it.
	 * 
	 * @param	initialVelocity
	 * 			The initial velocity that will be used to calculate the 
	 * 			jump distance.
	 * @return	The outcome off a fixed formula.
	 * 			| result == ((Math.pow(initialVelocity, 2) * 
	 *			|	Math.sin(2 * getVector().getDirection()))
	 *			|		/ STD_ACCEL_EARTH)
	 */
	@Raw
	public double calculateJumpDistance(double initialVelocity){
		return (Math.pow(initialVelocity, 2.0) * 
					Math.sin(2.0 * this.getVector().getDirection()))
						/ STD_ACCEL_EARTH;
	}
	
	/**
	 * Return the standard earth acceleration.
	 * 	The standard earth acceleration determines how fast the standard
	 * 	acceleration on Earth is.
	 */
	@Basic
	@Immutable
	public static double getSTD_ACCEL_EARTH(){
		return STD_ACCEL_EARTH;
	}
	
	/**
	 * Variable registering the Earth's standard acceleration(m/s²).
	 */
	private static final double STD_ACCEL_EARTH = 9.80665;
	
	/**
	 * Calculates the necessary action points of a worm to move with a 
	 * formula intended for it.
	 * 
	 * @param	nbSteps
	 * 			The numer of steps the worm would perform.
	 * @return	The outcome off a fixed formula which is dependant on the given 
	 * 			number of steps.
	 * 			| result == ((int) Math.ceil( nbSteps *
	 *			|	Math.abs(Math.cos(this.getVector().getDirection())+ 
	 *			|		4 * Math.sin(this.getVector().getDirection()))))
	 */
	@Raw
	public int calculateActionPointsToMove(int nbSteps){
		return (int) Math.ceil( nbSteps *
				Math.abs(Math.cos(this.getVector().getDirection())+ 
					4.0 * Math.sin(this.getVector().getDirection())));
	}
	
	/**
	 * Calculates the necessary action points of a worm to turn with a 
	 * formula intended for it.
	 * 
	 * @param	angle
	 * 			The angle that would be added to the current direction of this worm.
	 * @return	The outcome off a fixed formula which is dependant on the given angle.
	 * 			| result == ((int) Math.ceil(Math.abs((angle / (2 * Math.PI))
	 *			|				* 60)))
	 */
	@Raw
	public int calculateActionPointsToTurn(double angle){
		return (int) Math.ceil(Math.abs((angle / (2.0 * Math.PI))
					* 60.0));
	}
	
	/**
	 * Check whether this worm can move the given number of steps.
	 * 
	 * @param	nbSteps
	 * 			The number of moves the worm would move.
	 * @return	True if and only if the given worm its amount of action points is
	 * 			greater than or equal to the amount of action points that are
	 * 			required to perform the move and if the action points of this worm
	 * 			are strict positive, and if the given number of steps are strict positive.
	 * 			| result == this.getActionPoints() >= this.calculateActionPointsToMove(nbSteps)
	 *			| 	&& this.getActionPoints() > 0
	 *			| 		&& nbSteps > 0
	 */
	@Raw
	public boolean canMove(int nbSteps) {
		return this.getActionPoints() >= this.calculateActionPointsToMove(nbSteps)
				&& this.getActionPoints() > 0
					&& nbSteps > 0;
	}
	
	/**
	 * Moves this worm by the given number of steps.
	 * 
	 * @param	nbSteps
	 * 			The number of steps the worm will move by.
	 * @post	The new x coordinate for this worm is equal to a formula dependant on the
	 * 			given number of steps.
	 * 			| new.getVector().getCoordinate().getX() == this.getVector().getCoordinate().getX() +
				| 	stepDistance * Math.cos(this.getVector().getDirection())
	 * @post	The new y coordinate for this worm is equal to a formula dependant on the
	 * 			given number of steps.
	 * 			| this.getVector().getCoordinate().getY() +
				| 	stepDistance * Math.sin(this.getVector().getDirection())
	 * @post	The new action points of this new worm is equal to this worm its current action
	 * 			points minus the outcome of a formula intended to calculate the cost in action points
	 * 			of the given number of steps to move.
	 * 			| new.getActionPoints() == old.getActionPoints() - this.calculateActionPointsToMove(angle)
	 * @throws	IllegalMoveException(this, nbSteps)
	 * 			This worm can not perform this move.
	 * 			| !this.canMove(nbSteps)
	 */
	@Raw
	public void move(int nbSteps) throws IllegalMoveException{
		if (!this.canMove(nbSteps))
			throw new IllegalMoveException(this, nbSteps);
		double stepDistance = nbSteps * this.getRadius();

		double newX = this.getVector().getCoordinate().getX() +
				stepDistance * Math.cos(this.getVector().getDirection());
		
		double newY = this.getVector().getCoordinate().getY() +
				stepDistance * Math.sin(this.getVector().getDirection());		
		
		this.getVector().getCoordinate().setX(newX);
		this.getVector().getCoordinate().setY(newY);
		
		this.useActionPoints(this.calculateActionPointsToMove(nbSteps));
	}
	
	/**
	 * Check whether this worm can turn the given angle.
	 * 
	 * @param	angle
	 * 			The angle that the worm would move.
	 * @return	True if and only if the given worm its amount of action points is
	 * 			greater than or equal to the amount of action points that are
	 * 			required to perform the turn and if the action points of this worm 
	 * 			are strict positive, and if the worm will reach a valid angle by turning
	 * 			by the given angle.
	 * 			| result == this.getActionPoints() >= this.calculateActionPointsToTurn(angle) &&
	 *			| 	this.getActionPoints() > 0 && 
	 *			| 		this.getVector().canAcceptForChangeDirection(angle)
	 */
	@Raw
	public boolean canTurn(double angle) {
		return this.getActionPoints() >= this.calculateActionPointsToTurn(angle) &&
				this.getActionPoints() > 0 && 
				this.getVector().canAcceptForChangeDirection(angle);
	}
	
	/**
	 * Turns this worm by the given angle.
	 * 
	 * @param	angle
	 * 			The angle that the worm turns by.
	 * @post	The new direction for this worm is equal to current angle of this worm
	 * 			plus the given angle.
	 * 			| new.getVector().getDirection() == old.getVector().getDirection() +
				| 	angle
	 * @post	The new action points of this new worm is equal to this worm its current action
	 * 			points minus the outcome of a formula intended to calculate the cost in action points
	 * 			of the given angle to turn by.
	 * 			| new.getActionPoints() == old.getActionPoints() - this.calculateActionPointsToTurn(angle)
	 * @throws	IllegalTurnException(this, angle)
	 * 			The new worm can not have the given angle as its angle.
	 * 			| !this.canTurn(angle)
	 */
	@Raw
	public void turn(double angle) throws IllegalTurnException {
		if (!this.canTurn(angle))
			throw new IllegalTurnException(this, angle);
		this.getVector().changeDirection(angle);
		this.useActionPoints(this.calculateActionPointsToTurn(angle));		
	}
	
	
	/**
	 * Lets the given worm perform a jump.
	 * 
	 * @post	The new x-coordinate for this worm is equal to the current x-coordinate plus the distance
	 * 			of the jump which is calculated by a formula intended for it.
	 * 			| new.getVector().getCoordinate().getX() == old.getVector().getCoordinate().getX() + distance)
	 * @post	The new action points of this new worm is equal to this worm its current action
	 * 			points minus its current action points, and will thus be equal to zero.
	 * 			| new.getActionPoints() == old.getActionPoints() - old.getActionPoints()
	 * @throws	IllegalJumpException(this)
	 * 			This worm can not perform a jump.
	 * 			| !this.canJump()
	 */
	@Raw
	public void jump() throws IllegalJumpException {
		if (!this.canJump())
			throw new IllegalJumpException(this);
		double distance = this.calculateJumpDistance(
				this.calculateInitialJumpVelocity(
						this.calculateJumpForce()));
		
		this.getVector().getCoordinate().setX(this.getVector().getCoordinate().getX() +
				distance);
		this.useActionPoints(this.getActionPoints());
	}
	
	/**
	 * Calculates the time a jump of this worm would take.
	 * 
	 * @return	The time a jump of this worm would take.
	 * 			| result == distance / (initialVelocity * 
	 *			|				Math.cos(this.getVector().getDirection()))
	 * @throws	IllegalJumpException(this)
	 * 			The new worm can not perform a jump.
	 * 			| !this.canJump()
	 */
	@Raw
	public double getJumpTime() throws IllegalJumpException {
		if (!this.canJump())
			throw new IllegalJumpException(this);
		double initialVelocity = this.calculateInitialJumpVelocity(
				this.calculateJumpForce());
		double distance = this.calculateJumpDistance(initialVelocity);
		
		return distance / (initialVelocity * 
							Math.cos(this.getVector().getDirection()));
	}
	
	/**
	 * Calculates the position of a worm at a given moment, during a jump.
	 * 
	 * @param	t
	 * 			The time for which the position will be returned.
	 * @return	The position the worm should be located at on the given moment.
	 * 			| result[0] == this.getVector().getCoordinate().getX() +
	 *			|		this.calculateInitialJumpVelocity(this.calculateJumpForce()) *
	 *			|		Math.cos(this.getVector().getDirection()) * t;
	 * 			| result[1] == this.getVector().getCoordinate().getY() + (
	 *			|	this.calculateInitialJumpVelocity(this.calculateJumpForce()) *
	 *			|		Math.sin(this.getVector().getDirection()) * t - 
	 *			|			0.5 * STD_ACCEL_EARTH * Math.pow(t, 2));
	 */
	@Raw
	public double[] getJumpStep(double t) {
		double[] jumpStep = new double[2];
		jumpStep[0] = this.getVector().getCoordinate().getX() +
					this.calculateInitialJumpVelocity(this.calculateJumpForce()) *
						Math.cos(this.getVector().getDirection()) * t;
		jumpStep[1] = this.getVector().getCoordinate().getY() + (
					this.calculateInitialJumpVelocity(this.calculateJumpForce()) *
						Math.sin(this.getVector().getDirection()) * t - 
							0.5 * STD_ACCEL_EARTH * Math.pow(t, 2));
		return jumpStep;
	}
	
	/**
	 * Constructs and returns a worm, consisting of the given properties.
	 *
	 * @param	x
	 * 			The x-coordinate for this new worm.
	 * @param	y
	 * 			The y-coordinate for this new worm.
	 * @param	direction
	 * 			The direction for this new worm.
	 * @param	radius
	 * 			The radius for this new worm.
	 * @param	name
	 * 			The name for this new worm.
	 * @post	The new x-coordinate of this new worm is equal to the given x-coordinate. 
	 *     		| new.getVector().getCoordinate().getX() == x
	 * @post	The new y-coordinate of this new worm is equal to the given y-coordinate. 
	 *     		| new.getVector().getCoordinate().getY() == y  
	 * @post	The new direction of this new worm is equal to the given direction. 
	 *     		| new.getVector().getDirection() == direction
	 * @post	The new radius of this new worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new name of this new worm is equal to the given name.
	 * 			| new.getName() == name
	 * @post	The mass of this new worm is equal to the outcome of the formula
	 * 			intended for it.
	 * 			| new.getMass() == calculateMass(radius)
	 * @post	The new action points of this this new worm is equal to the given
	 * 			action points.
	 * 			| new.getActionPoints() == actionPoints
	 * @post	The maximum action points of this new worm is equal to the mass
	 * 			of this worm rounded to the nearest integer and then casted to an
	 * 			integer type.
	 * 			| new.getMaximumActionPoints() == (int) round(getMass())
	 * @return	The newly created worm.
	 * 			| result == new Worm(new Vector(new Coordinate(x,y), direction), 
	 *						radius,	name)
	 */
	public static Worm createWorm(double x, double y, double direction, double radius,
			String name) {
			return new Worm(new Vector(new Coordinate(x,y), direction), 
							radius,	name);
	}
}
