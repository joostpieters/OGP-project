package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of vectors involving a position and a direction.
 * 
 * @invar	The direction must hold a valid value.
 * 			| isValidDirection(getDirection())
 * @invar	Each worm can have its direction as its direction. 
 * 			| canHaveAsDirection(getDirection())
 *  
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */

public class Vector {

	/**
	 * Initialize this new vector with given coorinate, direction.
	 * 
	 * @param	position
	 * 				The position for this new vector.
	 * @param	direction
	 * 				The direction (angle in radians) for this new vector.
	 * @pre		The given direction must be a valid direction for any vector.
	 * 			| isValidDirection(direction)
	 * @post	The new position of this new vector is equal to the given
	 * 			position.
	 *      	| new.getCoordinate() == position
	 * @post	The new direction of this new vector is equal to the given
	 * 			direction. 
	 * 			| new.getDirection() == direction
	 */
	@Raw
	public Vector(final Position position, final double direction) {
		setDirection(direction);
		this.position = position;
	}

	/**
	 * Set the direction of this vector to the given direction.
	 * @param	direction
	 * 			The new direction for this vector.
	 * @pre		The given direction must be a valid direction for any vector.
	 * 			| isValidDirection(direction)
	 * @post	The new direction of this vector is equal to the
	 * 			given direction.
	 * 			| new.getDirection() == direction
	 */
	@Raw 
	private void setDirection(final double direction) {
		assert (isValidDirection(direction)) : "Precondition: acceptable direction";
		this.direction = direction;
	}
	
	/**
	 * Changes the direction for this worm to the current direction plus the
	 * given angle.
	 * 
	 * @param	turningAngle
	 * 			The angle to turn.
	 * @pre		This vector can have the given direction as its direction.
	 * 			| canHaveDirection(direction)
	 * @post	The new direction for this worm is equal to the given radius
	 * 			plus the current direction.
	 * 			| new.getDirection() == old.getDirection() + turningAngle 			
	 */
	@Raw
	public void changeDirection(final double turningAngle){
		assert (this.canAcceptForChangeDirection(turningAngle)) : 
					"Precondition: acceptable direction";
		this.direction = getDirection() + turningAngle;
	}
	
	/**
	 * Return the direction of this vector.
	 *	The direction of a vector determines how the vector is orientated.
	 */
	@Basic
	@Raw
	public double getDirection(){
		return this.direction;
	}
	
	/**
	 * Check whether this vector can have the given direction as its direction.
	 * 
	 * @param	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is not Not-A-Number and if
	 * 			the angle is greater than the minimum value for doubles and if
	 * 			the angle is less than the maximum value for doubles.
	 * 			| result == !Double.isNaN(pos)
	 */
	public static boolean isValidDirection(final double direction) {
		return !Double.isNaN(direction);
	}
	
	/**
	 * Return a boolean reflecting whether this vector can accept the given
	 * turning angle for changing the direction.
	 * 
	 * @param	turningAngle
	 * 			The turning angle to be checked
	 * @return	True if and only if direction of this vector incremented with
	 * 			the given turning angle is a valid direction for any vector,
	 * 			if that sum does not cause an overflow and if that sum does not
	 * 			cause an underflow.
	 * 			| result == isValidDirection(this.getDirection() + turningAngle) &&
	 *			|	this.getDirection()  <= Double.MAX_VALUE - turningAngle &&
	 *			|		this.getDirection() >= -(Double.MAX_VALUE) + Math.abs(turningAngle) &&
	 *			|			!(this.getDirection() < 0 && turningAngle == -Double.MAX_VALUE) &&
	 *			|				!(this.getDirection() > 0 && turningAngle == Double.MAX_VALUE)
	 */
	@Raw
	public boolean canAcceptForChangeDirection(final double turningAngle){
		return isValidDirection(this.getDirection() + turningAngle) &&
					this.getDirection()  <= Double.MAX_VALUE - turningAngle &&
						this.getDirection() >= -(Double.MAX_VALUE) + Math.abs(turningAngle) &&
							!(this.getDirection() < 0 && turningAngle == -Double.MAX_VALUE) &&
								!(this.getDirection() > 0 && turningAngle == Double.MAX_VALUE);
	}

	/**
	 * Variable registering the direction of this vector in radians.
	 */
	private double direction;
	
	/**
	 * Return the position of this vector.
	 *	The position of a vector determines where the vector is located.
	 */
	@Basic
	@Raw
	public Position getPosition(){
		return this.position;
		
	}
	
	/**
	 * Variable registering the direction of this vector in radians.
	 */
	private Position position;

}
