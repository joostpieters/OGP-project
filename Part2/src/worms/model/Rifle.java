package worms.model;

/**
 * A class of rifles involving mass, cost, damage, name
 * 	and propulsion power.
 * 
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public final class Rifle extends Weapon {

	/**
	 * Initialize this new rifle with fixed values.
	 *
	 * @post	The new mass of this new rifle is equal to a fixed value. 
	 *     		| new.getMass() == MASS
	 * @post	The new cost of this new rifle is equal to a fixed value. 
	 * 			| new.getCost() == COST
	 * @post	The new damage of this new rifle is equal to a fixed value. 
	 * 			| new.getDamage() == DAMAGE
	 * @post	The new name of this new rifle is equal to a fixed value. 
	 * 			| new.getName() == NAME
	 * @post	The new propulsion power of this new rifle is equal to a fixed value. 
	 * 			| new.getPropulsionPower() == PROPULSION_POWER
	 */
	public Rifle (){
		setMass(MASS);
		setCost(COST);
		setDamage(DAMAGE);
		setName(NAME);
		setPropulsionPower(PROPULSION_POWER);
	}
	
	/**
	 * Calculates and returns the force of this rifle, which is equal to its propulsion power.
	 * 
	 * @return	The force of this rifle, which is equal to its propulsion power.
	 * 			| result == getPropulsionPower()
	 */
	@Override
	protected double getForce(final int yield){
		return getPropulsionPower();
	}

	/**
	 * A variable registering the mass which applies for all rifles.
	 */
	private static final double MASS = 0.01;

	/**
	 * A variable registering the cost which applies for all rifles.
	 */
	private static final int COST = 10;

	/**
	 * A variable registering the damage which applies for all rifles.
	 */
	private static final int DAMAGE = 20;

	/**
	 * A variable registering the name which applies for all rifles.
	 */
	private static final String NAME = "Rifle";

	/**
	 * A variable registering the propulsion power which applies for all rifles.
	 */
	private static final double PROPULSION_POWER = 1.5;
}
