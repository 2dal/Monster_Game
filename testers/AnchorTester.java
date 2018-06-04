package testers;

import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.*;
import toolsForMonsters.StringFormatException;

public class AnchorTester {
	@Test public void AnchorTester1() throws StringFormatException{
		Monster example =new Monster("Example",20,20,19,20,5);
		Purse pur = new Purse();
		Weapon wep = new Weapon();
		example.pickUpObject(pur);
		example.pickUpObject(wep);
		assertEquals(((Purse)example.getObjectsInRightArm()),pur); //purse is in the right arm, weapon in the left
		assertEquals((Weapon)example.getObjectsInLeftArm(),wep);
		example.moveWeaponToRightHand(wep);
		assertEquals(((Purse)example.getObjectsInLeftArm()),pur); // now the purse is in the left arm and weapon in the right
		assertEquals((Weapon)example.getObjectsInRightArm(),wep);
		example.throwObject(wep);
		example.throwObject(pur);//empties his anchors

		
		Weapon wep2 = new Weapon(); // let's do the same with a only weapons. To show that we can replace a weapon in one hand with another one
		
		example.pickUpObject(wep);
		example.pickUpObject(wep2);
		assertEquals((Weapon)example.getObjectsInRightArm(),wep);
		assertEquals((Weapon)example.getObjectsInLeftArm(),wep2);
		example.moveWeaponToRightHand(wep2);
		assertEquals((Weapon)example.getObjectsInRightArm(),wep2);
		assertEquals((Weapon)example.getObjectsInLeftArm(),wep);
		example.throwObject(wep);
		example.throwObject(wep2);
		
	
	}
}
