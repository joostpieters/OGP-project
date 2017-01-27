package worms.model;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of bazooka's involving mass, cost, damage, name
 * 	and propulsion power.
 * 
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public final class Bazooka extends Weapon {

	/**
	 * Initialize this new bazooka with fixed values.
	 *
	 * @post	The new mass of this new bazooka is equal to a fixed value. 
	 *     		| new.getMass() == MASS
	 * @post	The new cost of this new bazooka is equal to a fixed value. 
	 * 			| new.getCost() == COST
	 * @post	The new damage of this new bazooka is equal to a fixed value. 
	 * 			| new.getDamage() == DAMAGE
	 * @post	The new name of this new bazooka is equal to a fixed value. 
	 * 			| new.getName() == NAME
	 * @post	The new propulsion power of this new bazooka is equal to a fixed value. 
	 * 			| new.getPropulsionPower() == PROPULSION_POWER
	 */
	@Raw
	public Bazooka(){
		setMass(MASS);
		setCost(COST);
		setDamage(DAMAGE);
		setName(NAME);
		setPropulsionPower(PROPULSION_POWER);
	}
	
	/**
	 * Calculates and returns the force of this bazooka, dependant on the given yield.
	 * 
	 * @param	yield
	 * 			The yield that will be used for the formula.
	 * @return	The force of this bazooka calculated with a fixed formula, dependant
	 * 			on the given yield.
	 * 			| result == getPropulsionPower() + 
	 *			|	(MAX_PROPULSION_POWER - getPropulsionPower()) * 
	 *			|	(yield / 100)
	 */
	@Override
	protected double getForce(final int yield){
		return getPropulsionPower() + 
				(MAX_PROPULSION_POWER - getPropulsionPower()) * 
				(yield / 100);
	}
	
	/**
	 * A variable registering the mass which applies for all bazooka's.
	 */
	private static final double MASS = 0.300;

	/**
	 * A variable registering the cost which applies for all bazooka's.
	 */
	private static final int COST = 50;

	/**
	 * A variable registering the damage which applies for all bazooka's.
	 */
	private static final int DAMAGE = 80;

	/**
	 * A variable registering the name which applies for all bazooka's.
	 */
	private static final String NAME = "Bazooka";

	/**
	 * A variable registering the propulsion power which applies for all bazooka's.
	 */
	private static final double PROPULSION_POWER = 2.5;

	/**
	 * A variable registering the max propulsion power which applies for all bazooka's.
	 */
	private static final double MAX_PROPULSION_POWER = 9.5;
}
