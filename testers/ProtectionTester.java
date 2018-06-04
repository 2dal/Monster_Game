package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;

public class ProtectionTester {
/**
 * This method tests the Protection. As the protection is worked out nominally, every error will be an assertion error
 * 
 * @throws StringFormatException
 */
	@Test public void ProtectionTests() throws StringFormatException {
		Monster reference = new Monster("Referense", 20,19, 3, 100, 3);
		try {
//			int HighNumber=reference.getMaxProtection();
			@SuppressWarnings("unused")
			Monster example = new Monster("Example",20,19,7,100, 3);
			fail("Protection too high");
		}
		catch (AssertionError e) {
		}
		
		
		
		
		try{
			@SuppressWarnings("unused")
			Monster example = new Monster("Example", 20,19,5, 100,3);
			fail();
		}
		catch(AssertionError e){

		}
		int EvenNumber;
		for(EvenNumber=2;EvenNumber<= reference.getMaxProtection();EvenNumber=EvenNumber+2) {
			try {
				@SuppressWarnings("unused")
				Monster example = new Monster("Example", 20,19, EvenNumber, 100, 3); 
				fail("Protection must be a prime, so it can't be an even number");
			}
			catch (AssertionError e) {
			}
		}
	}
}
