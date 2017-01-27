package worms.model;

import java.util.Stack;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of entities involving a mass.
 * 
 *
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public class Entity {
	
	/**
	 * Return the mass of this entity.
	 * 	The mass of a entity expresses the mass in kilograms from that entity.
	 */
	@Basic
	@Raw
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Set the mass for this entity to the given mass.
	 * 
	 * @param	mass
	 * 			The new mass for this entity.
	 * @post	The new mass for this entity is equal to the 
	 * 			given mass.
	 * 			| new.getMass() == mass
	 */
	@Model
	@Raw
	protected void setMass(final double mass){
		this.mass = mass;
	}
	
	/**
	 * Variable registering the mass (in kilograms) of this entity.
	 */
	private double mass;
	
	/**
	 * 
	 * @param	base
	 * 			The base radius for which the new radius will be calculated.
	 * @param	distance
	 * 			The maximum distance for the greatest returned radius.
	 * @param	inclusive
	 * 			Whether or not the final distance should be inclusive, or exlusive.
	 * 			If exclusive, the minimum value for doubles will be substracted from the current
	 * 			distance once it is equal to the maximum distance, before adding the radius.
	 * @param	precision
	 * 			The precision distance steps between the base radius and the final distance.
	 */
	public static Stack<Double> getRadiuses(final double base, final double distance, final boolean inclusive, final double precision){
		Stack<Double> radiuses = new Stack<Double>();
		
		for (double i = distance; 
				i > precision; 
				i -= precision) {
			
			if (i == distance && !inclusive)
				i -= Double.MIN_VALUE;
			radiuses.add(base + base * i);
		}
		return radiuses;
	}
}
