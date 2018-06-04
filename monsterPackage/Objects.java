package monsterPackage;
import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import toolsForMonsters.*;

/**
 * A class of shared properties of purse, backpack, weapon. 
 * 
 * @invar  Each object must have a valid ID.
 *       | isValidID()
 * @invar  Each object must have a valid weight in grams
 *       | validWeight(float grams)
 * @invar  Each object could be picked up in case it's not used by other monster or can exist by itself (purse) or is not terminated
 *       | isPickedUp()
 *       
 */

public abstract class Objects {


	/**
	 * Variable that registers the weight of the object.
	 */
	public float Weight;
	/**
	 * Variable that registers the value of the object.
	 */
	protected int Value;
	/**
	 * Varible that registers the identification of the object.
	 */
	public final long Identification; 
	/**
	 * List of all the used IDs. 
	 * Used for reference when a new object is constructed, so that one ID number is never used twice.
	 */
	private static ArrayList<Long> usedId= new ArrayList<Long>();
	/**
	 * Variable that registers what monster is in possession of the object.
	 */
	protected Monster Holder;
	/**
	 * Variable that registers what backpack is in possession of the object.
	 */
	protected Backpack InnBack;
	/**
	 * Boolean variable that registers if the object is terminated or not.
	 */
	private boolean isTerminated=false; 
	/**
	 * Boolean variable that registers if the object is picked up or not
	 */
	private boolean isPickedUp=false;
	/**
	 * Boolean variable that registers if the object is in a backpack or not
	 */
	private boolean inBackpack=false;
	/**
	 *	Create weapon's, purse's, backpack's shared properties. 
	 *
	 *	Properties of objects coded defensively
	 *
	 * @param  	Weight
	 *         	The Weight of the object.
	 * @param  	Holder    
	 * 			The Holder of the object.
	 * 
	 * @Post    The given weight value is positive
	 * 		 	|weight > 0; 
	 * 
	 * @Post    Weapons, purses, backpacks can be held by monsters
	 * 
	 * @throws  IllegalArgumentException 
	 *			| ! this.validWeight(Weight)
	 * 
	 * @throws 	IllegalArgumentException
	 *			| this.isPickedUp()==true



	 *  Properties of objects coded nominally
	 * 
	 * @param  	Value
	 * 			The value of the object.
	 * 
	 * @pre	    The value must be a positive number
	 *       	| validValue(float value)
	 * 
	 * 
	 * @effect The newValue of objects is set to  the given value of an object.
	 *         | setValue(new.)
	 * 


	 *  Properties of objects coded totally
	 *  
	 * @param  	Identification        
	 *         	The unique Identification number of the object.
	 *         
	 * @Post    The identification number of an object is a positive odd number
	 * 
	 * @post    The identification number of the object is valid
	 * 			|isValidID(identification)==true
	 * 
	 * @note	The identification is overridden in the purse class because of other constriction on valid IDs.
	 */

	protected Objects(float Weight, int Value) {
		// ID must be done totally (for weapons);	
		this.Identification=this.generateValidID();
		usedId.add(Identification);
		if (!this.validWeight(Weight)) // Weight must be done defensively (for weapons)
			throw new IllegalArgumentException("Weight must be a positive integer");
		else 
			this.Weight=Weight;
		assert(this.validValue(Value));
		this.setValue(Value); //value can't be negative, but the purse can be worthless
	}


	public ArrayList<Long> getUsedID() {
		ArrayList<Long> Temp = usedId;
		return Temp; 						// don't know why someone would want to change this, but now they can't 
	}
	public String printUsedID() {
		return Arrays.toString(usedId.toArray());
	}
	/**
	 * generates a valid ID for the object
	 * 
	 * @return	Valid identification
	 * 			|isValidID(result)==true
	 */
	public long generateValidID() {
		long ValidID=0;
		while (!this.isValidID(ValidID)) {

			ValidID= getRandom.getRandomLongNumber(1, Long.MAX_VALUE); 
		}
		return ValidID;

	}		

	/**
	 * 	Inspector isValidID checks if the long integer provided is a valid number to be used as a identification number.
	 *	The requirements is to be a long positive odd integer.
	 * 
	 * @param 	id
	 * 			The long integer that is checked if it's a valid ID .
	 * 
	 * @return 	True if and only if the given parameter is a positive odd number that is not currently in use by another object.
	 * 			|((id%)==0 && usedId.contains(id))
	 * 
	 */

	public boolean isValidID(long id) {
		boolean hue = false;
		if (id >= 0) {
			if ((id%2)==0 || usedId.contains(id)) 
				hue = false;
			else 
				hue = true;
		}	

		return hue;
	}

	/** Inspector getWeight
	 * 
	 * 	returns the weight of the object in grams as a float.
	 * 
	 * @return	Weight
	 */
	@Basic
	public float getWeight() {
		return Weight; // Weight is final so no reason to bother hiding the reference
	}

	/** Inspector getTotalWeight
	 * 
	 * Inspector that returns the total weight of the object. Is to be overridden most subclasses, will only return it's own weight if not overridden.
	 * 
	 */

	public float getTotalWeight() {
		return this.getWeight();
	}

	/**
	 * Inspector validWeight checks if the weight of an object is never negative
	 * 
	 * @param	The weight that is checked
	 * 
	 * @post  	This method returns true if the weight is valid
	 * 
	 * @return  boolean value
	 * 			|result==grams<=0
	 * 
	 */

	public boolean validWeight(float grams) {
		if (grams<=0)
			return false;
		else 
			return true;
	}


	/** Inspector getValue
	 * returns the  value of the object.
	 * 
	 * @return 	Value
	 * 			the value of the object
	 */
	@Basic 
	public int getValue() {
		int temp= this.Value; // value can be changed through set value, but not through this reference
		return temp;
	}
	
	/** Inspector getTotalValue
	 * 
	 * Inspector that returns the total value of the object. Is to be overridden two of the subclasses, and returns getValue() if it's not.
	 * 
	 * @return	The value of the object
	 * 			|getTotalValue()==getValue
	 */
	public int getTotalValue() {
		return this.getValue();
	}

	/**
	 * Mutator setValue
	 * Sets the value to the new object
	 * 
	 * @param 	newValue
	 * 
	 * @pre		This object is not terminated
	 * 			|!this.isTerminated()
	 * 
	 * @pre		The given value is a valid value for this object
	 * 			|validValue(newValue)==true
	 * 
	 * @post	The parameter given is the new value of the object
	 * 			|new.getValue()==newValue
	 */

	public void setValue(int newValue) {
		assert(!this.isTerminated());
		assert(this.validValue(newValue));
		this.Value=newValue;
	}
	
	/**
	 * 
	 * @param 	value
	 * 			Value that is to be checked
	 * 
	 * @return	returns true if and only if the parameter given is a positive integer
	 * 			|if (value>=0)
	 * 			|	return true
	 */
	public boolean validValue(float value) {
		if (value<0)
			return false;
		else {
			return true;
		}
	}

	/** Inspector getIdentification
	 * 
	 * Returns Identification
	 */

	public long getIdentification() {
		return this.Identification;
	}
	/**
	 * Inspector that checks if the object is terminated or not
	 * 
	 * @return true if and only if the object is terminated
	 */
	@Basic
	public boolean isTerminated() {
		boolean temp = this.isTerminated;
		return temp;
	}

	/** Method Terminate
	 * 
	 * Terminates object, makes its value equal to zero and is dropped by the holder.
	 * 
	 * @post	|isTerminated==true
	 * 
	 * @post	|value==0
	 * 
	 * @post	|Holder==null
	 * 
	 * @post	|isPickedUp==false
	 */

	public void Terminate() {
		this.Value=0;
		this.isTerminated=true;
		try {
		this.ThrowAway();
		}
		catch(IllegalArgumentException e) {
			
		}
		this.whenTerminated();
	}
	/**
	 * Method that is called when the object is terminated. 
	 * Does nothing initially but is overridden by subclasses that need special actions when terminated
	 * 
	 */
	private void whenTerminated() {

	}

	/** 
	 * Inspector getHolder
	 * 
	 * returns the holder of the object
	 * 
	 * @return Holder
	 */

	public Monster getHolder() {
		return this.Holder; // user already has lots of access to the monster
	}

	/**
	 * Inspector isPickedUp checks if the object was picked up by the monster.
	 * 
	 * @post 	This method returns true if the object was picked up
	 * 
	 * @return 	returns the isPickedUp variable
	 * 			|result == this.isPickedUp
	 * 			
	 * 
	 */
	public boolean isPickedUp() {
		boolean temp = this.isPickedUp;
		return temp;
	}
	/**
	 * Inspector CanBePickedUp checks if the object can be picked up by checking if it's already picked up or terminated. 
	 * 
	 * @post This method returns true if the object can be picked up
	 * 
	 * @return  true if and only if the object is not terminated or picket up
	 * 			if (this.isPickedUp()==false&&this.isTerminated()==false)
	 * 				return true
	 */

	public boolean CanBePickedUp() {
		if (this.isPickedUp()==false&&this.isTerminated()==false)  //add purse, it could be simply found on the floor and exist by itself
			return true;
		else
			return false;
	}


	/** Mutator that makes the object get picked up by a specific monster 
	 * 
	 * @param	Holder
	 *  		The monster that wishes to pick up the object
	 *  
	 * @post	The object is picked up by the monster
	 * 			|new.Holder.getObject(this)==this
	 * 
	 * @throws	IllegalArgumentException
	 * 			|isPickedUp()
	 * 
	 * @throws	IllegalStateException
	 * 			|isTerminated()
	 */

	public void PickUp(Monster Holder) {
		if (this.isPickedUp()==true)
			throw new IllegalArgumentException("this is allready picked up");
		else if(this.isTerminated())
			throw new IllegalStateException("Can't pick up a broken object");
		else if(Holder.allreadyPickedUp(this)) {
			this.isPickedUp=true;
			this.Holder=Holder; //Adds the object to the anchor if it's not already added,
		}
		else{
			this.isPickedUp=true;
			this.Holder=Holder;
			Holder.pickUpObject(this);
		}
	}

	/**
	 * Mutator that throws the object away from the holder
	 * 
	 * @post	
	 * 			|Holder==null
	 * 
	 * @throws	IllegalArgumentException
	 * 			Can't throw away an object that's not picked up
	 * 			|isPickedUp==false
	 */
	public void ThrowAway() {
		if (this.isPickedUp==false)
			throw new IllegalArgumentException("This object isn't picked up by anyone, and can't be throwed away");
		else {
			this.isPickedUp=false;
			try {
				Holder.throwObject(this);
			}
			catch(IllegalArgumentException e) {

			}
			this.Holder=null;
		}

	}
	/**
	 * Inspector insideBackpack checks if the object is inside a backpack. 
	 * 
	 * @post	this method returns true if the object is inside a backpack.
	 * 
	 * @return	boolean value
	 * 			|result == inBackpack
	 */
	public boolean insideBackpack() {
		boolean temp = this.inBackpack;
		return temp;
	}
	/**
	 * modifier that changes the inbackpack to true. Is only called by the subclass Backpack.
	 * @param	Back	
	 * 			The backpack the that the object is put inside
	 * 
	 * @post	
	 * 			|new.InnBack==Back
	 * 
	 * @post	
	 * 			|new.InBackpack == true
	 * 
	 * @throws	IllegalArgumentException
	 * 			|insideBackpack()==true
	 * 
	 * @throws	IllegalStateException
	 * 			|isTerminated()
	 */
	protected void putInsideABackpack(Backpack Back) {
		if (this.insideBackpack()==true)
			throw new IllegalArgumentException("This object is allready inside a backpack and can't be added again");
		else if(this.isTerminated())
			throw new IllegalStateException("Can't put a broken object into a backpack");
		else
			this.inBackpack=true;
			this.InnBack=Back; // TODO fiks slik at vi kan sjekke hvilken ryggsekk den er i 
	}
	/**
	 * modifier that changes the inbackpack to false. Is only called by the subclass Backpack.
	 * 
	 * @post	
	 * 			|inBackpack==false
	 * 
	 * @post	
	 * 			|InnBack==null
	 * 
	 * @throws	IllegalArgumentException
	 * 			|insideBackpack()==false
	 * 
	 * @throws	IllegalArgumentException
	 * 			|!isInBackpack(this)
	 */
	public void takeOutOfBackpack(Backpack back) {
		if (this.insideBackpack()==false)
			throw new IllegalArgumentException("This object can't be taken out of a backpack since it isn't inside a backpack");
		else if (!back.isInBackpack(this))
			throw new IllegalArgumentException("This object isn't inside the given backpack");
		else {
			this.inBackpack=false;
			this.InnBack=null;
			try {
				back.removeObject(this);
			}
			catch(IllegalArgumentException e) {
			}
		}
	}
	/**
	 * Inspector that returns the type of object in a string format
	 * 
	 * @return "object"
	 */
	@Basic
	public String Type() {
		return "object";
	}

}