package monsterPackage;
import java.util.ArrayList;
import java.util.regex.Pattern;
import be.kuleuven.cs.som.annotate.*;
import toolsForMonsters.*;

/**
 * A class defines properties of monster and contains method, responsible for fight
 * 
 * @invar  Each monster must have a valid name.
 * 		   The name should contain specific characters
 *       | isValidName(String namses)
           The length of the name should be at least 2
 *       | isValidNameLenght(String names)
           The first letter should be in uppercase
 *       | isValidNameStart(String names)

 * @invar  Each monster must have a valid hitpoint
 *       | is_natural(int born_hitpoint)
 *       | born_hitpoint >= 0

 * @invar  Each monster must have a valid protection
		 | isValidProtection
 * 
 */

public class Monster {


	private int damage;
	public final int strength; 
	private int hitpoint;
	private final java.lang.String theName;
	private final int protection;
	private static final int maxDamage = 20;
	private final int minDamage= 1;
	private final int maxProtection = 40; 
	private final int minProtection = 1; 
	private final int maxHitpoints = 100;
	private final int born_hitpoint;
	private int numberOfAnchors;                                 
	private ArrayList<Objects> anchors = new ArrayList<Objects>();
	private static ArrayList<Objects> availableObjects = new ArrayList<Objects>();


	/** Create a new monster who has a name, damage, strength, protection and hitpoints.



	 * Properties of monster coded defensively

	 * @param  	theName
	 *         	The Name of the monster.
	 * @param  	hitpoint
	 * 			The hitpoint of the monster. 
	 * @param  	born_hitpoint
	 * 			The hitpoint (random number) the monster was born with.
	 * @param   numberOfAnchors
	 * 			anchors of monster
	 *
	 * @Post    The given name consists of at least two characters, only contain letters, digits,
	 *			apostrophes and spaces, start with a capital letter.
	 *			| Pattern.matches("^[a-z A-Z 0-9 \' ]*$" == true
	 *			| lenght > 1
	 *			| Character.isUpperCase(names.charAt(0))
	 * 		 	| getName() == theName;         	
	 * @Post    Hitpoint is a natural number(including 0)
	 * 			|getHitpoint() == hitpoint;         	
	 * @Post    Number of anchors is higher than 3
	 * 			| numberOfAnchors > 3
	 * 
	 * @effect  The hitpoint of monster is set to  the given hitpoint of a monster.
	 *        	|  setHitpoint(int born_hitpoint)
	 * @effect  Sets the possible maximum hitpoint to monster's hitpoint
	 *        	|  setMaxHitpoint(int hitpoint)
	 * @effect  Sets the possible maximum hitpoint to monster's hitpoint
	 *        	|  setMaxHitpoint(int hitpoint)  
	 *        
	 * @throws	IllegalArgumentException
	 * 			Hitpoints must be a positive integer
	 * 			|!is_natural(hitpoint)
	 *
	 * @throws  StringFormatException 
	 *			| ! isValidName(String namses)
	 * @throws	IllegalArgumentException
	 *         	| numberOfAnchors<3


	 * Properties of monster coded totally

	 * @param  	damage
	 *         	The damage of the monster.
	 * @param  	strength        
	 *         	The strength of the monster.
	 * @param  	maxDamage
	 *         	The maximum posssible damage of the monster.
	 * @param  	minDamage        
	 *         	The minimum posssible damage of the monster.
	 *         	
	 * @Post    The damage is an integer number between 1 and 20, where minimum value is exactly 1.
	 *			| getDamage() == damage;
	 *			| 0 < Damage <= maxDamage()
	 * 			| if Damage < 1, |then damage = 1
	 * 			| if Damage > getMaxDamage(), damage = getMaxDamage()
	 * 			| if 0 < Damage < getMaxDamage(), then new.damage= Damage
	 * @Post    Srtenght is represented as a  scale of integer numbers, including negative numbers,
	 *			in which the average strength is 10
	 *			| getStrength() == strength;
	 *
	 * @effect  The damage of monster is set to  the given damage of a monster.
	 *        	| setDamage(int Damage)


	 * Properties of monster coded nominally
	 * 
	 * @param  	protection    
	 * 			The protection of the monster.
	 * @Post    The protection is a prime number between 1 and 40
	 * 			| getProtection() == protection;
	 * @effect  The protection of monster is set to  the given protection of a monster.
	         	| this.protection = protection


	 */

	public Monster(String name, int Damage, int strenght, int protection, int hitpoint, int numberOfAnchors) 
			throws StringFormatException{


		if (!isValidName(name)|| !isValidNameLenght(name)|| !isValidNameStart(name))
			throw new StringFormatException(name);
		else
			this.theName=name;							     // Sets name
		this.setDamage(Damage);		 					 // Sets damage
		this.strength=strenght;							 // Sets strenght
		this.protection = protection;					 // Sets protection
		this.born_hitpoint=hitpoint;					 // Sets born hitpoint

		if (is_natural(born_hitpoint)==false)
			throw new IllegalArgumentException("The hitpoints must be a positive integer"); 
		else
			this.setHitpoint(hitpoint);	// Sets hitpoints

		if (numberOfAnchors<3)
			throw new IllegalArgumentException("The number of anchors is too low. Monsters need at least 3");
		else
			this.numberOfAnchors=numberOfAnchors;
	}
	/**
	 * Inspector that returns the number of used anchors
	 * 
	 * @return	Number of anchors filled with objects
	 */
	
	@Basic
	public int getAnchors() {
		return this.anchors.size();
	}

	/**
	 * Basic constructor that generates a monster with valid name, 2 damage, 500 strenght, 7 protection, 100 hitpoint, 4 anchors
	 */
	
	public Monster() throws StringFormatException {
		this("Peter Griffin",2,500,7,100,4);
	}
	
	/** Methods concerning The Name
	 * 
	 */

	/**
	 * Inspector isValidName checks if the name user writes in has valid characters.
	 * 
	 * @param namses
	 * 
	 * @post True if and only if the Name of a monster is written correctly according to set instructions, if not it returns false
	 * 
	 * @return boolean value
	 * 		   | if Pattern.matches("^[a-z A-Z 0-9 \' ]*$", namses) == true
	 * 		   | then result == isValidName(String namses) == true
			   | if Pattern.matches("^[a-z A-Z 0-9 \' ]*$", namses) == false
	 * 		   | then result == isValidName(String namses) == false
	 * 
	 */

	public static boolean isValidName(String namses){
		if 	(Pattern.matches("^[a-z A-Z 0-9 \' ]*$", namses)==true)
			return true;
		else
			return false;
	}

	/**
	 * Inspector isValidNameLenght checks if the name is long enough.
	 * 
	 * @param namses
	 * 
	 * @post True if and only if the Name of a monster is longer than 2 characters
	 * 
	 * @return boolean value
	 * 			| if lenght > 1
	 * 			| then result == isValidNameLenght(String names) == true
	 *			| if lenght < 1
	 * 			| then result == isValidNameLenght(String names) == false
	 */

	public static boolean isValidNameLenght(String names) {
		int lenght = names.length();
		if (lenght > 1)
			return true;
		else 
			return false;
	}

	/**
	 * Inspector isValidNameStart checks if the name begins with an upper case letter. 
	 * 
	 * @param names
	 * 
	 * @post the code will return true only if the character begins with and upper case letter, if the name doesn't begin with a character and if the name begins with a lower case letter, the code will return false
	 * 
	 * @return boolean value
	 * 			| if Character.isLetter(names.charAt(0)) == true && Character.isUpperCase(names.charAt(0)) == true
	 * 			| then result == isValidNameStart(String names) == true
	 * 			| if Character.isLetter(names.charAt(0)) == false && Character.isUpperCase(names.charAt(0)) == false
	 * 			| then result == isValidNameStart(String names) == false 
	 * 
	 */

	public static boolean isValidNameStart(String names) {
		if ((Character.isLetter(names.charAt(0)))) {
			if (Character.isUpperCase(names.charAt(0)))
				return true;
			else 
				return false;
		}
		else
			return false;
	}

	
	/** 	
	 * Inspector that gives the name of this monster.
	 * 
	 * @post  The name consists of at least two characters,
	 * 		   contains letters, digits, apostrophes and spaces,
	 *		   start with a capital letter.
	 * @return name of this monster
	 *	   	   | Pattern.matches("^[a-z A-Z 0-9 \' ]*$" == true
	 *         | lenght > 1
	 *         | Character.isUpperCase(names.charAt(0)) == true
	 * 		   | result == return this.theName
	 *  
	 */

	@Basic
	public String getName() {
		return this.theName;
	}


	/**  Methods concerning Damage
	 * 
	 * 
	 * */
	

  	/** Mutator setDamage(int Damage)
  	 * 
	 * @param 	Damage
	 * 
	 * @returns valid damage 
	 * 			| if Damage >= maxDamage, result == this.damage = getMaxDamage()
	 * 			| if Damage <= 1, result == this.damage= Damage
	 * 		    | result == this.damage= Damage
	 */

	public void setDamage(int Damage) {
		if (Damage >= maxDamage)
			this.damage = getMaxDamage();
		else if (Damage <= 1)
			this.damage= minDamage;
		else
			this.damage= Damage;
	}


	/** Inspector getDamage
	 * 
	 * Returns  valid damage
	 *  		| if 0 < Damage <= maxDamage()
	 * 			| if Damage < 1, damage = 1
	 * 			| if Damage > getMaxDamage(), damage = getMaxDamage()
	 *			| if 0 < Damage < getMaxDamage(), then new.damage= Damage
	 * 			| result == getDamage()
	 * 
	 * @note 	temp is used so it will not be possible to change value through the getter
	 */

	@Basic
	public int getDamage() {
		int temp=this.damage;
		return temp;
	}


	/** Inspector getMaxDamage
	 * 
	 * Returns maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;	
	}



	/** Methods concerning hitpoints
	*/
	
	
	/** Setter set Hitpoints to a new value 
	 * 
	 * @param 	Hitpoint
	 * 
	 * @post	The new hitpoint is equal to the parameter given
	 * 			|new.getHitpoint()==Hitpoint
	 * 
	 * @throws	IllegalArgumentException
	 * 			The new hitpoint must be lower than the maximum hitpoints
	 * 			|this.getMaxHitpoint()<Hitpoint
	 * 
	 * @throws	IllegalArgumentException
	 * 			The new hitpoint must be a positive integer
	 * 			|Hitpoint<0
	 * 			
	 */

	public void setHitpoint(int Hitpoint) {
		if (Hitpoint > this.getMaxHitpoint())
			throw new IllegalArgumentException("Can't set the hitpoints larger than the max hitpoint");		
		else if (Hitpoint <= 0)
			throw new IllegalArgumentException("The hitpoint must be a positive integer");
		else
			this.hitpoint = Hitpoint;
	}


	/** Inspector getHitpoint
	 * 
	 * Returns hitpoint
	 * 
     * @note 	temp is used so it will not be possible to change value through the getter
	 */
	@Basic
	public int getHitpoint() {
		int temp =this.hitpoint;
		return temp;
	}


	/**
	 * Mutator setMaxHitpoint
	 * 
	 * @param 	hitpoint
	 * 
	 * @Post    Sets the possible maximum hitpoint to monster's hitpoint
	 * 			| hitpoint <= 0, result == this.hitpoint = 1
	 * 			| result == this.hitpoint = hitpoint
	 */

	public void setMaxHitpoint(int hitpoint) {
		if (hitpoint <= 0)
			this.hitpoint = 1;
		else
			this.hitpoint = hitpoint;
	}


	/** Inspector getMaxHitpoint
	 * 
	 * Returns maxHitpoints
	 * 
	 */
	
	public int getMaxHitpoint() {
		return maxHitpoints;
	}


	/**
	 * Inspector is_natural checks if born_hitpoint is a natural number
	 * 
	 * @param born_hitpoint
	 * 
	 * @return boolean value if the born_hitpoint is a natural number
 	 * 			| if born_hitpoint >= 0
	 * 			|then result == is_natural(int born_hitpoint) == true
	 * 			| if born_hitpoint < 0
	 * 			|then result == is_natural(int born_hitpoint) == false
	 * 
	 */

	private boolean is_natural(int born_hitpoint){
		if (born_hitpoint >= 0) {
			return true;
		}
		else
			return false;
	}

	/**
	 * Method reduceHitpoints(int Value). This method can also heal the monster in those rare occasions a monster with negative strength hits
	 * 
	 * @param Value
	 * 
	 * @Post	This method only reduces the born_hitpoint of the monster, therefore negative values will be treated as positive.
	 * 		    | Value >= this.getHitpoint()
	 * 			| then result == this.hitpoint=0;
	 * 			| if Value < =this.getHitpoint()
	 * 			| then result == hitpoint=this.hitpoint-Value;
	 *
	 */
	
	private void reduceHitpoints(int Value){
			if (Value>=this.getHitpoint())
				this.hitpoint=0;
			else
				this.hitpoint=this.hitpoint-Value;
	}


	/**  Methods concerning Protection
	 * */

	
	/**
	 * Inspector getProtection
	 * 
	 * @param  	protection
	 * 
	 * @return 	protection of the monster
	 *
	 */

	@Basic 
	public int getProtection() {
		return protection;
	}



	/**
	 * isValidProtection checks if the given protection value is valid
	 * 
	 * @param 	n
	 * 			Integer that is checked
	 * 
	 * @return boolean value
	 * 			| if n > maxProtection|| n < minProtection && IsPrime.Prime(n) == true
	 * 			| then result == isValidProtection(int n) == true
	 * 			| if n > maxProtection || n < minProtection && IsPrime.Prime(n) == false
	 * 			| then result == isValidProtection(int n) == false
	 */

	public boolean isValidProtection(int n) {
		if (n > maxProtection|| n < minProtection )
			return false;
		else if (IsPrime.Prime(n)!=true)
			return false;
		else 
			return true;
	}

	/**
	 * Getter for max protection
	 * 
	 * Returns Maximum Protection
	 */

	@Basic 
	public int getMaxProtection() {
		return this.maxProtection;
	}


	/**
	 * Inspector getStrength 
	 * 
	 * Returns the strength of a monster
	 * 
	 */

	@Basic 
	public int getStrength() {
		return this.strength;
	}
	
	/** Method getCarryCapacity() returns the capacity the monster is able to carry
	 * 
	 * @return integer that represents the carry capacity
	 * 
	 * @note	The capacity of the monster is given its own method in case it will ever be subjected to change
	 */

	@Basic
	public int getCarryCapacity() {
		int carryCapacity=getStrength()*12000;
		return carryCapacity;
	}
	
	/** getWeightCarried()
	 * 
	 * 
	 * @returns Total weigth carried by monster
	 * 			| if (this.anchors.get(i)!=null)
	 *			| then result == TotalWeigth=TotalWeigth+this.anchors.get(i).getTotalWeight()
	 *	
	 */

	public float getWeightCarried() {
		float TotalWeigth=0;
		for (int i=0; i< anchors.size();i++) {
			if(this.anchors.get(i)!=null)
				TotalWeigth=TotalWeigth+this.anchors.get(i).getTotalWeight();
		}			
		return TotalWeigth;
	}
	
	
	/**
	 * Inspector that returns the total value of everything the monster is carrying.
	 * 
	 * @return integer that represents the total value in dukats of everything the monster is carrying.
	 *			| result == TotalValue=TotalValue+item.getTotalValue()
	 */
	
	
	public int getTotalValue() {
		int TotalValue=0;
		for(Objects item:this.anchors) {
			TotalValue=TotalValue+item.getTotalValue();
		}
		return TotalValue;
	}

	/** Methods concerning anchors
	 *  
	 * @param  	thing
		  		The object that is held by the monster.
	 *
	 * @Post    Thing is placed on the available anchor with the lowest index number.
	 * 			|new.getObject(thing) == thing
	 * 
	 * @throws	IllegalArgumentException
	 * 			The monster doesn't have any available anchors to place the object in.
	 * 			|getAnchors()>=numberOfAnchors
	 * 
	 * @throws	IllegalStateException
	 * 			If the monster is dead it can't pick anything up
	 * 			|getHitpoint()==0
	 * 
	 * @throws	IllegalArgumentException
	 *			The object is already picked up by the monster, so it can't pick it up again
	 *			|allreadyPickedUp(thing)
	 * 
	 * @throws	IllegalArgumentException
	 * 			The Object can't be picked up 
	 * 			|!CanBePickedUp(thing)
	 * 
	 * @throws	IllegalArgumentException
	 * 			The monster can't pick up the object as it will make the total weight carried 
	 * 			by the monster exceed the weight the monster is able to carry 
	 *			|this.getWeightCarried()+thing.getTotalWeight()>this.getCarryCapacity()
	 * 
	 */


	public void pickUpObject(Objects thing) {
		if(this.getAnchors()>=this.numberOfAnchors) 
			throw new IllegalArgumentException("No anchors available to pick up objects");
		else if(this.getHitpoint()==0)
			throw new IllegalStateException("A dead monster can't pick anything up");
		else if(this.allreadyPickedUp(thing))
			throw new IllegalArgumentException("This object is allready picked up by this monster"); 
		else if(thing.CanBePickedUp()==false)
			throw new IllegalArgumentException("This object can't be picked up because it's either allready picked up by someone or it's terminated");
		else if(this.getWeightCarried()+thing.getTotalWeight()>this.getCarryCapacity())
			throw new IllegalArgumentException("This monster can't carry so much.");
		else {
			anchors.add(thing);
			try {
				thing.PickUp(this);
			}
			catch(IllegalArgumentException e) {

			}
		}
	}
	/** 
	 * Method moveWeaponToRightHand(Weapon wep)
	 * 
	 * @param 	wep
	 * 			The weapon that will be moved to the monsters right hand position
	 * 
	 * @post	The weapon is in the right hand of the monster, which translates to anchor number 0
	 * 			|new.getObjectsInRightArm()==wep
	 * 
	 * @throws	IllegalArgumentException
	 * 			If the weapon isn't in the anchors, the mutator can't change the anchor position.
	 * 			|!this.allreadyPickedUp(wep)
	 */
	public void moveWeaponToRightHand(Weapon wep) {
		if (!this.allreadyPickedUp(wep))
			throw new IllegalArgumentException("Weapon is not picked up and can't be moved to right hand");
		else {
			this.anchors.remove(wep);
			this.anchors.add(0,wep);
		}
	}
	/** 
	 * Method moveWeaponToLeftHand(Weapon wep)
	 * 
	 * @param 	wep
	 * 			The weapon that will be moved to the monsters left hand position
	 * 
	 * @post	The weapon is in the left hand of the monster, which translates to anchor number 1
	 * 			|new.getObjectsInLeftArm()==wep
	 * 
	 * @throws	IllegalArgumentException
	 * 			If the weapon isn't in the anchors, the mutator can't change the anchor position.
	 * 			|!this.allreadyPickedUp(wep)
	 */
	public void moveWeaponToLeftHand(Weapon wep) {
		if (!this.allreadyPickedUp(wep))
			throw new IllegalArgumentException("Weapon is not picked up and can't be moved to right hand");
		else {
			this.anchors.remove(wep);
			this.anchors.add(1,wep);
		}
	}
	/**
	 * Method allreadyPickedUp(Objects thing)
	 * 
	 * @param 	thing
	 * 			The object that is checked
	 * 
	 * @return	True if and only if the object thing is in one of the anchors of the monster
	 * 			| result == this.anchors.chontains(thing)
	 */
	
	public boolean allreadyPickedUp(Objects thing) {
		return this.anchors.contains(thing);
	}
	/**
	 * Returns the object that resides in the right hand of the monster. The right hand has the index number of 0 in the anchors
	 * 
	 * @return	 	The object that is held in the right arm. Right hand has the index number of 0 in the anchors
	 * 				| if this.anchors.size()>1
	 * 				| then result == anchors.get(0)
	 * 				| if this.anchors.size()<1
	 * 				| then result == null
	 * 
	 * @throws		IndexOutOfBoundsException
	 * 				If the monster hasn't picked up anything there wont be anything in the right hand. 
	 * 				|numberOfUsedAnchors()<=0
	 */

	@Basic
	public Objects getObjectsInRightArm() {
		if (this.anchors.size()<1)
			return null;
		else
			return 	this.anchors.get(0);
	}

	/**
	 * Getter that returns the object that resides in the left hand of the monster. The left hand has the index number of 1 in the anchors
	 * 
	 * @return	 	The object that is held in the right arm. Left hand has the index number of 1 in the anchors
	 * 				|anchors.get(1)
	 * 
	 * @throws		IndexOutOfBoundsException
	 *				If the monster has picked up 1 or fewer objects, there wont be anything in the left hand
	 *				|numberOfUsedAnchors()<=1
	 */
	
	@Basic
	public Objects getObjectsInLeftArm() {
		return this.anchors.get(1);
	}



	/**
	 *
	 * Getter that returns the object that resides in the left hand of the monster. The left hand has the index number of 1 in the anchors
	 *  
	 * @return	 	The object that is held in the right arm. The back of the monster has the index number of 2 in the anchors
	 * 				|anchors.get(2)
	 * 
	 * @throws		IndexOutOfBoundsException
	 *				If the monster has picked up 2 or fewer objects, there wont be anything on the back of the monster
	 *				|numberOfUsedAnchors()<=2
	 */

	@Basic		
	public Objects getObjectsInBackAnchors() {
		return anchors.get(2);
	}
	/**
	 * 	Getter that returns the object at a specific anchor number. this getter exists because we want to give 
	 * 	the user access to the objects in the monsters possession, but not the whole array 
	 * 
	 * 	@param 		AnchorNumber
	 * 				The index number of the object  
	 * 
	 *	@return		Objects
	 *				Object held at given anchor number
	 *				|getObjects(i)==anchors.get(i)
	 *
	 *	@throws		IndexOutOfBoundsException
	 *				If the parameter is higher than the total number of used anchors
	 *				|numberOfUsedAnchors()-1<AnchorNumber
	 */
	@Basic
	public Objects getObject(int AnchorNumber) {
		return this.anchors.get(AnchorNumber);
	}
	/**
	 * 	Getter that returns the object at a specific anchor name. this getter exists because we want to give 
	 * 	the user access to the objects in the monsters possession, but not the whole array
	 * 
	 * 	This does exactly the same as getObjectNumber, but it's easier to remember the name of an object rather than the current index number of an object.
	 *  Especially since the index number is subjected to changes.
	 * 
	 *  @param 	Name
	 *  		Name of a object you wish to get.
	 * 
	 * 	@return Object with the given Name
	 * 			|this.anchors.get((this.anchors.indexOf(Name)))==getObject(Name)
	 * 			
	 * 
	 * 	@throws IllegalArgumentException 
	 * 			If the backpack does not contain a Object with the name given as a parameter
	 * 			|!this.anchors.contains(Name)
	 */
	public Objects getObject(Objects Name) {
		if(!this.anchors.contains(Name))
			throw new IllegalArgumentException("The monsters anchors does not contain object with the given name.");
		else
			return this.anchors.get((this.anchors.indexOf(Name)));
	}
	/**
	 * Gets the first available backpack in the anchors if there is one
	 * @return 	Backpack
	 * 			| if (item instanceof Backpack) 
	 * 			| then result == return (Backpack) item
	 */
	public Backpack getBackpack() {

		for(Objects item : this.anchors) {
			if (item instanceof Backpack)
				return (Backpack) item;
		}

		return null;
	}
	
	/**
	 * Removes the object that resides in the right hand of the monster. The right hand has the index number of 0 in the anchors
	 * 
	 * @Post		| anchors.get(0) != null
	 * 				| then result == throwObject(0)
	 * 
	 * @throws		IndexOutOfBoundsException
	 * 				If the monster hasn't picked up anything there wont be anything in the right hand. 
	 * 				|numberOfUsedAnchors()<=0
	 */

	
	public void throwObjectRightArm() {
		if (anchors.get(0) != null) {
			this.throwObject(0);
		}
	}

	/**
	 * Removes the object that resides in the left hand of the monster. The right hand has the index number of 0 in the anchors
	 * 
	 * @Post		| anchors.get(1) != null
	 * 				| then result == throwObject(1)
	 * 
	 * @throws		IndexOutOfBoundsException
	 * 				If the monster hasn't picked up anything there wont be anything in the left hand. 
	 * 				|numberOfUsedAnchors()<=1
	 */
	
	public void throwObjectLeftArm() {
		if (anchors.get(1) != null) {
			this.throwObject(1);
		}
	}

	/**
	 * Frees occupied anchor by an object of the monster
	 * 
	 * @param		AnchorNumber
	 * 
	 * @Post		| anchors.get(AnchorNumber) != null && this.anchors.get(AnchorNumber).isPickedUp()
	 * 				| then result == this.anchors.get(AnchorNumber).ThrowAway() && this.anchors.remove(AnchorNumber)
	 * 
	 * @throws		IndexOutOfBoundsException
	 * 				If the monster hasn't picked up anything there wont be anything in the hand. 
	 * 				|numberOfUsedAnchors()<=AnchorNumber
	 */
	
	public void throwObject(int AnchorNumber) {
		if (anchors.get(AnchorNumber) != null) {
			if (this.anchors.get(AnchorNumber).isPickedUp()) {
				this.anchors.get(AnchorNumber).ThrowAway();
				this.anchors.remove(AnchorNumber);
			}
			else
				this.anchors.remove(AnchorNumber);
		}
	}
	
	/**
	 * Removes the object of the monster
	 * 
	 * @param		thing
	 * 
	 * @Post		The object is removed from the anchors.
	 * 				| this.anchors.get(anchors.indexOf(thing)).isPickedUp()
	 * 				| then result == thing.ThrowAway() && this.anchors.remove(thing)
	 * 
	 * @throws		IllegalArgumentException
	 * 				Can't throw an object that is not in the monsters possession.
	 * 				|!this.anchors.contains(thing)
	 */

	public void throwObject(Objects thing) {
		if (!this.anchors.contains(thing))
			throw new IllegalArgumentException("This object is not in the monsters posession, and can therefor not be thrown away");
		else if (this.anchors.get(anchors.indexOf(thing)).isPickedUp()) {
			thing.ThrowAway();
			this.anchors.remove(thing);
		}
		else
			this.anchors.remove(thing);
	}
	/**
	 * inspector that checks the number of used anchors.
	 * 
	 * @return integer that represents the number of used anchors.
	 */
	public int numberOfUsedAnchors() {
		return this.anchors.size();
	}
	
	
	/**
	 * This method removes an Object from the anchors and returns it.
	 *  
	 * @param Anchornumber
	 * 
	 * @return the Object it removes from the anchors
	 * 				| result == this.anchors.remove(Anchornumber)
	 */
	
	
	public Objects giveObject(int Anchornumber) {
		return this.anchors.remove(Anchornumber);
	}
	
	/**
	 * This method removes objects from monster who lost the fight
	 *  
	 * 	@param 			theLooser
	 * 					The monster that is 
	 * 
	 *	@Post			Monster throws all objects he had
	 * 					| result == Monster.availableObjects.add(theLooser.giveObject(i))
	 */

	private void IAmLooted(Monster theLooser) {  
		for (int i = 0; i < theLooser.numberOfUsedAnchors(); i++) {   
			Monster.availableObjects.add(theLooser.giveObject(i));

		}
	}
	/**
	 * This method allows the winner to pick objects from looser's possesions
	 *  
	 * @param theWinner
	 * 
	 * @Post 		Winner gets theobjects until he runs of capacity
	 * 				| theWinner.getBackpack()!=null
	 * 				| result == theWinner.getBackpack().add(item)
	 * @throws		IllegalArgumentException
	 * 
	 * @note		If the winner has a backpack, this part of the method will try to fill 
	 *				the backpack with all the Objects in the availableObjects array, and catch the exceptions
	 *				Second part of the method tries to put all the objects in the availableObjects array 
	 *				into the winners anchors, and catches the IllegalArgumentExceptions that are thrown
	 *				when it's unsuccessful. 
	 *				Third part of the method removes all the elements in the availableObjects array 
	 * 
	 */

	private void IAmWinner(Monster theWinner) {  
		if (theWinner.getBackpack()!=null) {
			for (Objects item:Monster.availableObjects) {
				try {
					theWinner.getBackpack().add(item);
				}
				catch(IllegalArgumentException e) {

				}
			}
		}
		

	
		for (Objects item:Monster.availableObjects) 
			try {
				theWinner.pickUpObject(item);
			}
		catch(IllegalArgumentException e) {

		}
		
			
		Monster.availableObjects.clear(); 
	}





	public  String getTypeObject() {
		ArrayList<String> Things= new ArrayList<String>();
		if (anchors.size()>0) {
			for (int i = 0; i < anchors.size(); i++) {
				Things.add(anchors.get(i).Type());
			}
			return Things.toString();
		}
		else
			return "null";
	}


	/**
	 * Inspector getStats 
	 * 
	 * Prints the relevant statistic referring to current fight
	 * 
	 */

	public void getStats() {
		String newLine = System.getProperty("line.separator");
		if (this.anchors.size()>0) {
			if(this.getBackpack()!=null)
				System.out.println(newLine + "Name: "+this.theName+newLine +"Hitpoints: "+this.getHitpoint()+newLine +"Damage: " +this.getDamage()+newLine +"Strenght: "+ this.getStrength()+newLine +"Protection:"+this.getProtection()+newLine + "Number of anchors:" + this.numberOfAnchors +newLine +newLine+ "Anchors contain: "+getTypeObject() + " Backpack contain: " + this.getBackpack().getTypeObjectBackpack() );
			else
				System.out.println(newLine + "Name: "+this.theName+newLine +"Hitpoints: "+this.getHitpoint()+newLine +"Damage: " +this.getDamage()+newLine +"Strenght: "+ this.getStrength()+newLine +"Protection:"+this.getProtection()+newLine + "Number of anchors:" + this.numberOfAnchors +newLine +newLine+ "Anchors contain: "+getTypeObject() + " Backpack contain: Nothing ");
		}
		else
			System.out.println(newLine + "Name: "+this.theName+newLine +"Hitpoints: "+this.getHitpoint()+newLine +"Damage: " +this.getDamage()+newLine +"Strenght: "+ this.getStrength()+newLine +"Protection:"+this.getProtection()+newLine + "Number of anchors:" + this.numberOfAnchors +newLine +newLine+ "Anchors contain: Nothing");
	}//TODO fix 

	/**
	 * This method is responsible for hitting of the monsters
	 *  
	 * @param warrior
	 * 
	 * @Post 		The fight happens while opponent.getHitpoint()>0 && attacker.getHitpoint()>0
	 * 				| if opponent.getHitpoint()<=attacker.getProtection()
	 * 				| then result == theLooser = opponent && theWinner = attacker && IAmLooted(theLooser) &&IAmWinner(theWinner);
	 * 				| if opponent.getHitpoint()==0)
	 * 				| then result == theLooser = opponent && theWinner = attacker && IAmLooted(theLooser) &&IAmWinner(theWinner);
	 *			
	 */


	public void hitting(Monster warrior) {
		Monster theWinner;
		Monster theLooser;
		int random=0;				 // initiating the random value
		int attackNumber=0;
		int damageNumber = 0;		// We don't want to hit a dead monster or have a dead monster be hitting
		int maxAttack;				// to calculate the maximum attacknumber
		Monster attacker;
		Monster opponent;
		System.out.println();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println();
		System.out.println("Let the figth begin!");
		System.out.println();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

		int firstTurn = getRandom.getRandomNumber(1,2);
		if (firstTurn == 1) {
			attacker = warrior;
			opponent = this;
		}
		else {
			attacker = this;
			opponent = warrior;
		}




		while (opponent.getHitpoint()>0 && attacker.getHitpoint()>0) {

			if (attacker ==warrior) {      //Swapping attacker and opponent for next roun
				attacker = this;
				opponent = warrior;
			}
			else {
				attacker = warrior;
				opponent = this;
			}
			System.out.println("---------------------------------------------");
			System.out.println("-> Now " + attacker.getName() + " will attack");


			random = getRandom.getRandomNumber(0,30); 	// generates a random number between 0 and 30, placed inside the if loop so that every hit will generate a new random number
			if (random <= attacker.getHitpoint()) { 	// AttackNumber can't be higher than the hitpoints of attacker, this if/else loop checks the number and set it to born_hitpoint if the random number is too high.
				attackNumber=random;
			}
			else {
				attackNumber=attacker.getHitpoint(); 	// If the random number is higher 
			}


			if (attacker.anchors.size()>0 && attacker.anchors.get(0).Type().equals("weapon")) {
				//System.out.println(attacker.getName() + " has weapon in right hand");

				if (attackNumber>=opponent.getProtection()) {
					damageNumber = ((Weapon) attacker.anchors.get(0)).getDamage() + attacker.getDamage()+((getStrength()-5)/3);
					opponent.reduceHitpoints(damageNumber);
					System.out.println("The damage done "+damageNumber);
					System.out.println("Whoooah, " + attacker.getName() + " hit with a weapon! " + opponent.getName() + " now has " + opponent.getHitpoint() + " health left");

				}
				else {
					System.out.printf("%s missed his hit%n",attacker.getName());
				}
			}
			//If attacker has no weapon
			else if (attackNumber>=opponent.getProtection()) {
				damageNumber = attacker.getDamage()+((getStrength()-5)/3);
				opponent.reduceHitpoints(damageNumber);
				System.out.println("The damage done "+damageNumber);
				System.out.println(attacker.getName() + " has no weapon but the hit still succeded! " + opponent.getName() + " now has " + opponent.getHitpoint() + " health left");
			}
			else {
				System.out.printf("%s missed his hit%n",attacker.getName());
			}

			
			if (opponent.getAnchors()>0&&opponent.getObjectsInRightArm() instanceof Weapon)
				 maxAttack=opponent.getHitpoint()+(((Weapon) opponent.getObjectsInRightArm()).getDamage());
			else
				maxAttack=opponent.getHitpoint();
			if (opponent.getHitpoint()==0) {
				System.out.println();
				System.out.println("---------------------------------------------");
				System.out.println("The game is over, we have a winner - " + attacker.getName());
				theLooser = opponent;
				theWinner = attacker;
				IAmLooted(theLooser);
				IAmWinner(theWinner);
				theWinner.getStats();
				System.exit(1); 
			}
			else if (maxAttack<=attacker.getProtection()) {
				System.out.println();
				System.out.println("---------------------------------------------");
				System.out.println("Oh, such a shame, " + opponent.getName() + ", your attacks will never defeat " + attacker.getName());
				System.out.println("The game is over, we have a winner - " + attacker.getName());
				theLooser = opponent;
				theWinner = attacker;
				IAmLooted(theLooser);
				IAmWinner(theWinner);
				theWinner.getStats();
				System.exit(1); 
			}	
			else {
				System.out.println();
				System.out.println(attacker.getName() + " has " + attacker.getHitpoint() + " health");
				System.out.println(opponent.getName() + " has " + opponent.getHitpoint() + " health");
				continue;
			}

		}
	}
}
