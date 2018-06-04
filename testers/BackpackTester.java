package testers;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import monsterPackage.*;

public class BackpackTester {
	@Test public void isValidCapacity() {
		try {	// what happens when we create a backpack with negative capacity 
			@SuppressWarnings("unused")
			Backpack example = new Backpack(1,1,-1);
			fail("Illegal capacity, should be positive");
		}
		catch (IllegalArgumentException e) {
			}
		try {	// What happens when we overfill the backpack? one heavy object
			Backpack example = new Backpack(20,20,20);
			float sixe = example.getCapacity();
			Weapon toBig = new Weapon(sixe+1,1,1);
			example.add(toBig);
			fail("Can't add objects larger than capacity of backpack");
		}
		catch (IllegalArgumentException e) {
		}
		try { // What happens if we add too many light objects? 
			Backpack example = new Backpack();
			for (int i = -1;i<example.getCapacity();i++) {
				example.add(new Weapon(1,1,1));
			}
			fail("Can't add so many objects that it exceedes the weight limit.");
		}
		catch(IllegalArgumentException e) {
			
		}
		try {
			Backpack example = new Backpack();
			List<Objects> list = new ArrayList<Objects>();
			for (int i = -1 ;i<example.getCapacity();i++) {
				example.add(new Weapon(1,1,1));	// Adds one to many objects to the list than what the backpack can handle
				}
			ListIterator<Objects> iter = list.listIterator();
			while (iter.hasNext())
				example.add(iter.next());			// Adds all the objects to the backpack, until the exception is thrown
			fail("Can't add this many objects, as it exceeds the weight capacity");
			}
		catch (IllegalArgumentException e) {
			
		}
		Backpack example = new Backpack(40,20,400);
		Weapon wep = new Weapon(1,1,1);
		Purse pur = new Purse(1,1,1,1);
		example.add(wep);
		assertTrue(example.getObject(0)==wep); // check if the object was added
		example.removeObject(wep);
		try {
			assertTrue(example.getObject(0)==wep);
			fail("The object was not removed");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		// try the same, just for multiple objects
		example.add(wep,pur);
		assertTrue(example.getObject(0)==wep); // check if the weapon was added
		assertTrue(example.getObject(1)==pur); // check if the purse was added
		example.removeMultipleObjects(wep,pur);
		try {
			assertTrue(example.getObject(0)==wep);  // We are only catching IndexOutOfBoundsExceptions, which means that if any of 
													// the objects failed to be removed, we will get some sort of error thats not caught 
			fail("The object was not removed");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	}
}
