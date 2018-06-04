package monsterPackage;
import java.util.ArrayList;
import be.kuleuven.cs.som.annotate.*;

/**		 
* Class initializing backpack's special properties
* @invar  The object exists in a backpack
*      	  | isInBackpack(Objects thing)
*/

public class Backpack extends Objects {
	
	/**
	 * Float that represents the capacity of the backpack.
	 */
	private final float Capacity; 
	/**
	 * ArrayList where the collection of items inside the backpack is registered.
	 */
	private ArrayList<Objects> backpack= new ArrayList<Objects>();


	/** Create a new backpack that has a weight, value, capacity
	 * 
	 * Properties of backpack coded defensively
	 * 
	 * @param  	Weight
	 *         	The weight of the backpack. 
	 * @param   Capacity
	 * 			The capacity of the backpack. 
	 * 
	 * @Post    The given weight value is positive
	 * 		 	|weight > 0;         	
	 * @Post    The given capacity is positive
	 * 			|capacity > 0       	
 	 *
	 * @throws  IllegalArgumentException 
	 *			| ! Capacity<0
	 * @throws 	IllegalArgumentException
				| !this.backpack.contains(Name)
	 * @throws 	IllegalArgumentException
				| this.isInBackpack(item)
	 * @throws 	IllegalArgumentException				
				| getTotalWeight()+item.getTotalWeight()> this.getCapacity()				
	 * @throws 	IllegalArgumentException
	 * 			| item==this
	 * @throws 	IllegalArgumentException				
	 *			| item.insideBackpack()==true
	 *
	 *
	 *
	 * Properties of backpack coded nominally

	 * @param  	Value
	 * 			The value of the backpack.
	 * 
	 * @Pre	    The value must be a positive number
     *       	| validValue(float value)
     *       
     * @post    The value is a positive number
	 * 
	 */

	public Backpack(float Weight, int Value, float Capacity) {
		super(Weight, Value);
		if (Capacity<0)
			throw new IllegalArgumentException("Capacity can't be negative");
		else
			this.Capacity=Capacity;		
	}
	/**
	 * Basic constructor, creates a backpack with 40 weight, 20 value and 200 capacity.
	 */
	
	public Backpack() {
		this(40, 20, 2000);
	}
	/**
	 * Inspector getCapacity()
	 * 
	 * @return float that represents the capacity of the backpack
	 */
	@Basic
	public float getCapacity() {
		return this.Capacity;
	}
	/**
	 * Inspector getTotalWeight()
	 * 
	 * 	@return float that returns the total weight of the object, which includes the weight of everything inside the object.
	 * 			| if backpack.get(i)!=null
	 * 			| result == TotalWeigth=TotalWeigth+this.backpack.get(i).getTotalWeight()
	 */
	
	@Basic
	public float getTotalWeight() {
		float TotalWeigth=this.getWeight();
		for (int i=0; i< backpack.size();i++) {
			if(backpack.get(i)!=null)
				TotalWeigth=TotalWeigth+this.backpack.get(i).getTotalWeight();
		}			
		return TotalWeigth;
	}

	/** Method add(Objects... things)
	 * 
	 * This method checks if the object can be added to the backpack and adds it if all the conditions are right.
	 * The method can add a both a single object and a series of objects
	 * 
	 * @param 	things
	 * 
	 * @post	The thing is inside the backpack
	 * 			|getObject(thing)==thing
	 * 
	 * @throws	IllegalStateException
	 * 			Can't add something to a terminated backpack
	 * 			|isTerminated()
	 * 
	 * @throws	IllegalArgumentException
	 * 			|isInBackpack(things)
	 * 
	 * @throws	IllegalArgumentException
	 * 			|this.getTotalWeight()+item.getTotalWeight()> this.getCapacity()
	 * 
	 * @throws	IllegalArgumentException
	 * 			Can't put the backpack inside itself
	 * 			|things==this
	 * 
	 * @throws	IllegalArgumentException
	 * 			Can't put something into the backpack if it's already in another backpack
	 * 			|insideBackpack()
	 * 
	 */
	public void add(Objects... things) {
		for(Objects item : things) {
			if(this.isTerminated())
				throw new IllegalStateException("Can't add anything into a broken backpack");
			else if (this.isInBackpack(item))
				throw new IllegalArgumentException("The object you are trying to add is allready in the backpack");
			else if((this.getTotalWeight()-this.getWeight()+item.getTotalWeight())> this.getCapacity())
				throw new IllegalArgumentException("The backpack will be too heavy if you add this object");
			else if(item==this)
				throw new IllegalArgumentException("Can't put the backpack in itself");
			else if(item.insideBackpack()==true)
				throw new IllegalArgumentException("Can't pick up that item, since it's allready in a backpack"); 
			else if(item.isPickedUp()==true&&item.insideBackpack()==false) { 
				// if the monster has picked up the object first it must throw it away and put it in the backpack.
				//no need to check weight of object since the netto carried weight does not change
				item.ThrowAway();
				this.backpack.add(item);
				item.putInsideABackpack(this);
			}
			else {
				if(this.Holder!=null) {
					if(this.Holder.getCarryCapacity()<this.getTotalWeight()+item.getTotalWeight()) {
						throw new IllegalArgumentException("The monster carrying this backpack isn't strong enough to add this ");
					}
				}
				else {
					this.backpack.add(item);
					item.putInsideABackpack(this);
				}
			}
		}
	}
	/**
	 * Mutator removeObject(Objects thing) that removes one specific object from the backpack.
	 * 
	 * @param 	thing
	 * 
	 * @post	The object is no longer inside this backpack
	 * 			|new.backpack.SizeOf()== this.backpack.SizeOf()-1
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (!isInBackpack(thing))
	 */
	public void removeObject(Objects thing) {
		if (!this.isInBackpack(thing))
			throw new IllegalArgumentException("The object you are trying to remove isn't in the backpack");
		else
			thing.takeOutOfBackpack(this);
		this.backpack.remove(thing);
	}
	/**
	 * Inspector isInBackpack(Objects thing) that checks if object in question is in this backpack or not.
	 * 
	 * @param 	thing
	 * 			Object that is checked
	 * 
	 * @return 	true if and only if the backpack contains requested object
	 * 			|result == backpack.contains(thing))
	 */
	
	public boolean isInBackpack(Objects thing) {
		return this.backpack.contains(thing);
	}
	/** Method that returns the lightest object in the backpack
	 * 
	 *	@return Lightest object inside the backpack, if there are two objects that are the lightest it will return the first one.
	 *			|for (int i=0;i<this.backpack.size();i++) {
	 *			|	if(this.backpack.get(i).getWeight()<Light.getWeight())
	 * 			|		Light=this.backpack.get(i);
	 *			|	return Light = this.backpack.get(i)
	 *			
	 *	@throws	IllegalArgumentException
	 *			If the backpack is empty, the method can't return anything
	 *			|backpack.isEmpty()
	 *				
	 */
	public Objects LightestObject() {
		Objects Light=this.backpack.get(0);
		if (this.backpack.isEmpty())
			throw new IllegalArgumentException("No objects in the backpack");
		else {
			for (int i=0;i<this.backpack.size();i++) {
				if(this.backpack.get(i).getWeight()<Light.getWeight())
					Light=this.backpack.get(i);
			}
			return Light;
		}
	}
	/** Method that returns the heaviest object in the backpack
	 * 
	 * 	@return Heaviest object inside the backpack, if there are two objects that are the heaviest it will return the first one.
	 * 			| this.backpack.get(i).getWeight()>Heavy.getWeight()
	 * 			| Heavy=this.backpack.get(i)
	 * 
	 *	@throws	IllegalArgumentException
	 *			If the backpack is empty, the method can't return anything
	 *			|backpack.isEmpty()
	 */
	public Objects HeaviestObject() {
		Objects Heavy=this.backpack.get(0);
		if (this.backpack.isEmpty())
			throw new IllegalArgumentException("No objects in the backpack");
		else {
			for (int i=0;i<this.backpack.size();i++) {
				if(this.backpack.get(i).getWeight()>Heavy.getWeight())
					Heavy=this.backpack.get(i);
			}
			return Heavy;
		}
	}
	/**Inspector that checks if there is a backpack inside this backpack.
	 * 
	 * @return   backpack
	 * 			| if this.backpack.get(i) instanceof Backpack
	 * 			| result == isThereABackpack() == true
	 */
	public boolean isThereABackpack() {
		boolean backpack=false;
		for (int i=0;i<this.backpack.size();i++) {
			if (this.backpack.get(i) instanceof Backpack)
				return true;
		}
		return backpack;
	}
	/**
	 * getAllStuff(Backpack bag) transfers all the objects from this backpack into the backpack used as argument
	 * 
	 * @param 		bag
	 * 				The backpack that we are getting all the stuff from
	 * 
	 * @Post		Objects are transfered
	 * 				| result == bag.emptyBackpack();
	 * 
	 * @throws		IllegalArgumentException
	 * 				| if this.getTotalWeight()+bag.getTotalWeight()>this.getCapacity()
	 */
	public void getAllStuff(Backpack bag) {
		if(this.getTotalWeight()+bag.getTotalWeight()>this.getCapacity())
			throw new IllegalArgumentException("not enough room in this backpack to get all the objects");
		else {
			for(int i=0;i<bag.backpack.size();i++) {
				this.backpack.add(bag.backpack.get(i));
			}
			bag.emptyBackpack();
		}
	}
	/**
	 * This is an inspector that returns a string with everything inside the backpack.
	 * 
	 * @return content of backpack
	 */
	public String inventoryCheck() {
		return this.backpack.toString();
	}

	/**
	 * Method  emptyBackpack()
	 * 
	 * @Post		Removes all the objects in the backpack
	 * 				| result == this.backpack.clear()
	 */

	public void emptyBackpack() {
		for(int i=0;i<this.backpack.size();i++) { // changes the inBackpack to false for all the objects
			this.backpack.get(i).takeOutOfBackpack(this);
		}
		this.backpack.clear();
	}
	/**
	 * Method removeMultipleObjects(Objects... aSeriesOfObjects)
	 * 
	 * @param aSeriesOfObjects
	 * 
	 * @Post	Removes a multiple number of objects from the backpack
	 * 			| result == this.removeObject(item)
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (!isInBackpack(thing))
	 */
	public void removeMultipleObjects(Objects... aSeriesOfObjects) {
		for(Objects item : aSeriesOfObjects) {
			this.removeObject(item);
		}
	}
	/**
	 * Inspector that returns a float integer that represents the total value of the backpack in dukats.
	 * 
	 * @return TotalValue
	 */
	public int getTotalValue() {
		int TotalValue=this.getValue();
		for (Objects item:this.backpack) {
			TotalValue=TotalValue+item.getTotalValue();
		}			
		return TotalValue;
	}
	/**
	 * 	Getter that returns the object at a specific anchor number. this getter exists because we want to give 
	 * 	the user access to the objects in the monsters possession, but not the whole array 
	 * 
	 * 	@param AnchorNumber = Index number of the object in the anchor.
	 * 
	 *	@return The object at the anchor number 
	 *			| this.backpack.get(AnchorNumber)
	 *
	 *	@throws the array has a built in function that throws an ArrayIndexOutOfBoundsException.
	 */
	public Objects getObject(int AnchorNumber) {
		return this.backpack.get(AnchorNumber);
	}
	/**
	 * 	Getter that returns the object with a specific name.
	 * 
	 * 	@param Name
	 * 
	 * 	@return Object with the name of the @param
	 * 			| if this.backpack.contains(Name)
	 * 			| then result == this.backpack.get((this.backpack.indexOf(Name)))
	 * 
	 * 	@throws IllegalArgumentException if the backpack does not contain a Object with the name given as a parameter
	 * 			| !this.backpack.contains(Name)
	 * 
	 * 	@note This getter exists because we want to give 
	 * 		  The user access to the objects in the monsters possession, but not the whole array
	 * 		  This does exactly the same as getObject above, but it's easier to remember the name of an object rather than the current index number of an object.
	 * 		  Especially since the index number is subjected to changes.
	 */
	public Objects getObject(Objects Name) {
		if (!this.backpack.contains(Name))
			throw new IllegalArgumentException("The backpack contains no object with the given name.");
		else
			return this.backpack.get((this.backpack.indexOf(Name)));
	}

	/**
	 * Method that returns type of the object as String
	 * 
	 * 	@return String describing an object
	 * 
	*/
	@Basic @Immutable
	public String Type() {
		return "backpack";
	}

	/**
	 * Method that returns type of the object as String
	 * 
	 * 	@return String describing an object
	 * 			| this.backpack.size()>0
	 * 			| then result == Things.add(backpack.get(i).Type())
	 * 			| this.backpack.size()<0
	 * 			| then result == null    
	 * 
	 * 
	 * 
	*/
	
	public  String getTypeObjectBackpack() {
		ArrayList<String> Things= new ArrayList<String>();
		if (this.backpack.size()>0) {
			for (int i = 0; i < backpack.size(); i++) {
				Things.add(backpack.get(i).Type());
			}
			return Things.toString();
		}
		else
			return null;
	}

	/**
	 * Method that returns size of the backpack array, number of items stored in backpack
	 * 
	 * 	@return Number of items in a backpack
	 * 
	*/
	
	public int numberOfItems() {
		return this.backpack.size();
	}
	
	/**
	 *Method that empties the backpack when it's terminated
	 *
	 *	@post	
	 *			|this.backpack.size()==0
	 */
	
	public void whenTerminated() {
		this.backpack.removeAll(backpack); 
	}

}
