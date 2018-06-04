package monsterPackage;

import be.kuleuven.cs.som.annotate.*;

/**	 * Class initializing weapons special properties
 * 
 * @invar  Each weapon must have a valid damage.
 *       | validDamage(int f)
 *       
 **/

public class Weapon extends Objects {

	/**
	 * Variable registering the damage of the weapon
	 */
	private int Damage;
	/**
	 * Variable registering the maximum damage of the weapon
	 */
	private int MaxDamage = 20;
	/**
	 * Variable registering the minimum damage of the weapon
	 */
	public final int MinDamage = 1;


	/**
	 *	Constructor that initializes the weapon with given weight, value and damage
	 *
	 *	 *
	 * @param  	Weight
	 *         	The Weight of the object.
	 * @param  	Holder    
	 * 			The Holder of the object.
	 * 
	 * @Post    The given weight value is positive
	 * 		 	|weight > 0; 
	 * @Post    Weapons, purses, backpacks can be held by monsters
	 * 
	 * @throws  IllegalArgumentException 
	 *			| ! this.validWeight(Weight)
	 * @throws 	IllegalArgumentException
	 *			| this.isPickedUp()==true



	 *  Properties of objects coded nominally
	 * 
	 * @param  	Value
	 * 			The value of the object.
	 * 
	 * @param  	Damage
	 *         	The Damage of the weapon.
	 *         
	 * @param  	MaxDamage
	 *         	The Maximum Damage of the weapon.
	 *         
	 * @param  	MinDamage        
	 *         	The Minimum Damage of the weapon.
	 *         
	 * @pre	    The value must be a positive number
	 *       	| validValue(float value)
	 *       
	 * @Pre     The value of a weapon and purse is expressed in an amount of dukats
	 * 
	 * @Pre     The damage could be expressed as an integer number between 1 and 20
	 * 
	 * @post    The value of the object is a positive number
	 * 
	 * @post	The damage is an integer between 1 and 20
	 *			|f>this.MaxDamage || f<this.MinDamage	 * 
 
	 * @effect The newValue of objects is set to  the given value of an object.
	 *         | setValue(int newValue)
	 *
	 *
	 *  Properties of objects coded totally
	 *  
	 * @Post    The identification number of a weapon and backpack is a positive odd number
	 * 
	 * @Post	Purse has an identification number in the form of a long integer number of 
	 * 			the Fibonacci series
	 * 
	 * @post    The identification number of the object is valid
	 * 
	 * Properties of objects coded nominally
	 *
	 */


	public Weapon(float weight, int value, int Damage) {
		super(weight,value); 
		assert(this.validDamage(Damage));
		this.Damage = Damage;
	}
	/**
	 *Basic constructor, creates a weapon with 20 weight, 20 value and 20 capacity.
	 */
	public Weapon() {
		this(20,20,20);
	}

	/**
	 * Inspector validDamage(int f)
	 * 
	 * @param	f
	 * 			The integer that will be checked
	 * 
	 * @return 	True if and only if the damage is between 0 and 20
	 * 			|MaxDamage>f>MinDamage
	 * 			|result == validDamage(f)==true
	 * 
	 */

	public boolean validDamage(int f) {
		if (f>this.MaxDamage||f<this.MinDamage)
			return false;
		else
			return true;
	}


	/** Inspector getDamage
	 * 
	 * Returns Damage
	 */
	
	@Basic
	public int getDamage() {
		int temp = this.Damage;
		return temp;
	}

	/**
	 * This method checks what type of object this is
	 * 
	 * @return	"weapon"
	 * 			Weapon will always return "weapon"
	 * 			|"weapon"==Type()
	 * 
	 * @note	This method is created to check in the hitting method if the backpack/anchors of a monster contains object of type weapon
	 */
	@Basic
	public String Type() {  //This method is created to check in the hutting if the backpack of a monster contains object of type weapon
		return "weapon";
	}


}
