package monsterPackage;
import be.kuleuven.cs.som.annotate.*;
import toolsForMonsters.*;

/**	 * Class initializing purses's special properties
	 * 
	 * @invar  Each purse must have a valid ID.
	 *       | validPurseID(long id)
	 * @invar Checks if the capacity given is a valid capacity or not.
	 *       | IsLegalCapacity
	 *       | capacity > 0
	 *     
	 */

public class Purse extends Objects {
	/**
	 * Variable registering the capacity of the purse 
	 */
	private final int Capacity;
	/**
	 * Variable registering the amount of Dukats in the purse
	 */
	private int Dukats;
	/**
	 * Variable registering the type of the purse
	 */
	private final String Type = "purse";
	/**
	 * Initialize new Purse with given weight, value, capacity
	 *
	 * @invar  Each purse must have a valid ID.
	 *       	| validPurseID(long id)
	 *. 
	 * @param  	Capacity
	 *         	The Capacity of the purse.
	 *         
	 * @param  	Dukats
	 *         	Dukats express value of the purse.
	 *         
	 * @param  	Weight
	 *         	The Weight of the object.        	
	 *
	 * @Pre		Dukats can be added,  or removed from a purse as long as they don't
	 * 			reach purse's max capacity
	 * 
	 * @Post    The given weight value is positive
	 * 		 	|weight > 0; 
	 * 
	 * @Post    Weapons, purses, backpacks can be held by monsters
	 * 
	 * @post	The new capacity of the purse is equal to the given initial amoutn
	 *         	|new.getCapacity==capacity
	 * 
	 * @post	New amount of dukats in this new purse is equal to the given initial amount
	 * 			|new.getDukats==Dukats
	 *
	 * @throws  IllegalArgumentException 
	 *			| ! this.validWeight(Weight)
	 *
	 * @throws 	IllegalArgumentException
	 *			| this.isPickedUp()==true
	 *
	 * @Throws  IllegalArgumentException
	 *			| If the constructor was not given a legal integer for capacity
	 *			| !this.IsLegalCapacity(Capacity)
	 *
	 * @Throws	IllegalArgumentException
	 * 			| Number of dukats can not be negative
	 * 			| moreDukats<0
	 *
	 * @Throws  IllegalStateException
	 * 			| Can't remove Dukats from a destroyed purse.
	 * 			| lessDukats > this.getDukats()
	 * 	 
	 * 
	 * Properties of purse coded nominally
	 * 
	 * @param  	Value
	 * 			The value of the purse.
	 * 
	 * @pre	    The value must be a positive number
	 *       	| validValue(float value)
	 * 
	 * @Pre     The value of a weapon and purse is expressed in an amount of dukats
	 * 
	 * @post    The value of the object is a positive number
	 * 
	 * @effect The newValue of objects is set to  the given value of an object.
	 *         | setValue(int newValue)
	 * 
	 * @Post	Purse has an identification number in the form of a long integer number of 
	 * 			the Fibonacci series
	 * 
	 * @post    The identification number of the object is valid
	 * 			|this.validPurseID(Identification)==true
	 */

	public Purse(float Weight, int Value,int Capacity, int Dukats) {
		super(Weight,  Value);
		if (!this.IsLegalCapacity(Capacity))
			throw new IllegalArgumentException("The constructor was not given a leagal integer for capacity");
		this.Capacity=Capacity;
		this.addDukats(Dukats);
	}	
	
	/**
	 * Basic constructor that generates a empty purse
	 */
	public Purse() {
		this(10,10,200,0);
	}
	/**
	 * Checks if the capacity given is a valid capacity or not.
	 * 
	 * @param 	capacity
	 * 			The capacity of the purse
	 * 
	 * @return	True if and only if the capacity given is a positive integer. 
	 * 			|if (capacity>=0)
	 * 			|	return true
	 *
	 */
	public boolean IsLegalCapacity(int capacity) {
		if (capacity<0)
			return false;
		else
			return true;

	}
	/**
	 * Returns the amount of dukats held by the purse at the moment of inspection.
	 * 
	 * @return	Dukats
	 * 			|result==this.Dukats
	 */
	public int getDukats(){
		int temp = this.Dukats;
		return temp;
	}
	/**
	 * Method that 
	 * 
	 * @param 	moreDukats
	 * 
	 * @post	The content of the purse is increased by the amount 
	 * 			|new.getDukats == this.getDukats + moreDukats
	 * 
	 * @throw	IllegalArgumentException
	 * 			Can't add a negative value
	 * 			|moreDukats<0
	 * 
	 * @throw	IllegalStateException
	 * 			Can't add dukats to a ruined purse
	 * 			|isTerminated()
	 */
	public void addDukats(int moreDukats) {
		if (moreDukats<0) {
			throw new IllegalArgumentException("Can't add a negative sum");
		}
		else if (!this.isTerminated()) {
			if (getDukats()+moreDukats<=this.getCapacity())
				this.Dukats=this.Dukats+moreDukats;
			else {
				System.out.println("The purse was overfilled and teared. It falls to the ground and loses all its value.");
				this.Terminate();
			}
		}
		else
			throw new IllegalStateException("Can't add Dukats to a terminated purse.");
	}
	/**
	 * 
	 * @param 	lessDukats
	 * 			The amount of Dukats removed from the purse
	 * 
	 * @post	This purse contains less dukats
	 * 			|new.Dukats== this.Dukats-lessDukats
	 * 
	 * @throws	IllegalArgumentException
	 * 			Not enough Dukats in the purse to remove the amount given
	 * 			|lessDukats>this.Dukats()
	 * 
	 * @throws	IllegalStateException
	 * 			This purse is already terminated
	 * 			|this.isTerminates()
	 */
	public void removeDukats(int lessDukats) {
		if (!this.isTerminated()) {
			if(lessDukats>this.getDukats())
				throw new IllegalArgumentException("There is not enough dukats in this purse");
			else
				this.Dukats=this.Dukats-lessDukats;
		}
		else
			throw new IllegalStateException("Can't remove Dukats from a destroyed purse.");

	}
	/**
	 * Inspector	getTotalValue
	 * 				Inspector that returns the total value of the purse. 
	 * 				
	 * @return		totalValue
	 * 				Value of the purse added with the number of dukats held by the purse
	 * 				|totalValue= this.getValue() + this.Dukats
	 * 
	 */
	public int getTotalValue() {
		int totalValue=this.getValue() + this.Dukats;
		return totalValue;
	}
	
	/**
	 * Inspector getCapacity
	 * 
	 * @return	Capacity
	 * 			The capacity of this purse.
	 */
	@Basic
	public int getCapacity() {
		return this.Capacity;
	}
	
	/** Inspector 	getTotalWeight
	 * 
	 * @return	 	totalWeight
	 * 				Weight of the purse added with the weight of each Dukat held by the purse. The Dukats weigh 50 gram per coin.
	 * 				|totalWeight == this.getWeight() + this.getDukats*50
	 */

	public float getTotalWeight() {
		float totalWeight= this.getWeight()+this.getDukats()*50;
		return totalWeight;
	}
	
	/**
	 * This method checks what type of object this is
	 * 
	 * @return	"purse"
	 * 			Purses will always return "purse"
	 * 			|"purse"==Type()
	 * 
	 * @note	This method is created to check in the hitting method if the backpack/anchors of a monster contains object of type purse
	 */
	@Basic 
	public String Type() {  
		return this.Type;
	}
	
	
	/** Method generateValidID
	 * 
	 * 				The ID for a purse needs to be a Fibonacci number. This method uses getRandom.getRandomFibonacci
	 * 				to generate a random and then checks if the 
	 * 
	 * 	@return		long ID
	 * 				returns a valid long ID 
	 * 
	 * 	@note		Since fibonacci number increase drastically for each number and ID is represented by a long integer,
	 * 				we can have maximum 90 identical purses before the method starts to struggle. Assumed that no other 
	 * 				object uses a fibonacci number as identity (which is not a safe assumption if we are creating a lot of objects)
	 */
	public long generateValidID() {
		long ID = getRandom.getRandomFibonacci();
		while (!validPurseID(ID)) { // if the random number generated is not a valid one, it will try again
			ID = getRandom.getRandomFibonacci();
		}
		return ID;
	}

	/**
	 * Inspector validPurseID
	 * 
	 * @return 	True if and only if the id is a fibonacci number 
	 * 			not currently used as an ID number by another purse
	 * 			|result == (IsFibonacci.Fib(id)&& !this.getUsedID().contains(id))
	 * 
	 */

	public boolean validPurseID(long id) { // we need a checker for valid purse ID
		if (IsFibonacci.Fib(id)==true&& !this.getUsedID().contains(id)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Method whenTerminated 
	 * 
	 * 	@post	No Dukats in this purse
	 * 			|new.Dukats==0
	 * 
	 * 	@note	Is called when this purse is terminated and makes sure the contents are removed.
	 */
	public void whenTerminated() {
		this.Dukats=0;
	}


}
