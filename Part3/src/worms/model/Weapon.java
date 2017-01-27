package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of weapons involving mass, cost, damage, name
 * 	and propulsion power.
 * 
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public abstract class Weapon extends Entity {

	/**
	 * Return the cost of this weapon. The cost of a weapon expresses the amount of action points
	 * 	that will be used to shoot this weapon.
	 */
	@Basic
	@Raw
	public int getCost() {
		return this.cost;
	}
	
	protected void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * A variable registering the cost of a weapon.
	 */
	private int cost;
	
	/**
	 * Return the name of this weapon. The name of a weapon expresses the name of this weapon.
	 */
	public String getName() {
		return this.name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A variable registering the name of a weapon.
	 */
	private String name;
	
	/**
	 * Return the damage of this weapon. The damage of a weapon expresses the amount of hit points
	 * 	that will be conducted from a hit worm.
	 */
	public int getDamage() {
		return this.damage;
	}
	
	protected void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * A variable registering the damage of a weapon.
	 */
	private int damage;
	
	/**
	 * Return the propulsion power of this weapon. The propulsion power of a weapon expresses 
	 * the power of a propulsion of this weapon.
	 */
	public double getPropulsionPower() {
		return this.propulsionPower;
	}
	
	protected void setPropulsionPower (double power) {
		this.propulsionPower = power;
	}
	
	/**
	 * A variable registering the propulsion power of a weapon.
	 */
	private double propulsionPower;

	/**
	 * Calculates and returns the force of this weapon, dependant on the given yield.
	 * 
	 * @param	yield
	 * 			The yield that will be used for the formula.
	 * @return	The force of this weapon.
	 */
	protected abstract double getForce(int yield);
}
