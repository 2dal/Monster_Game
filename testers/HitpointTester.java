package testers;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import monsterPackage.*;
import toolsForMonsters.StringFormatException;

public class HitpointTester {
	
	@Test public void TooLowInitialHPtest() throws StringFormatException {
		try {// Negative HP in constructor
		@SuppressWarnings("unused")
		Monster example = new Monster("Example", 7,5,7,-1,7);
		fail("The hitpoint can't be negative");
		}
		catch(IllegalArgumentException e) {
		
		}
		try {// Too high HP in constructor
		Monster ref = new Monster();
		@SuppressWarnings("unused")
		Monster example = new Monster("Example", 7,5,7,ref.getMaxHitpoint()+1,7);
		fail("The hitpoint can't too high");
				
		}
		catch(IllegalArgumentException e) {
			
		}
	}
	@Test public void ReduceHPtest() throws StringFormatException {
		Monster example = new Monster();
		try {
		example.setHitpoint(-1); 
		fail("The hitpoint can't be set to a negative integer even after construction");
		}
		catch(IllegalArgumentException e) {
			
		}
		try {
			example.setHitpoint(example.getMaxHitpoint()+1);
			fail("The hitpoints can't be larger than the max hitpoint");
		}
		catch(IllegalArgumentException e) {
			
		}
	}
	
}
