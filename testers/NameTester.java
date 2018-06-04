package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;

public class NameTester {
	@Test public void IsValidNameLenghtValid() throws StringFormatException {
		try {
			@SuppressWarnings("unused")
			Monster example = new Monster("Ex%ample", 20,19, 5, 100,3);
			fail("Illegal symbols");
		}
		catch (StringFormatException e){
		}
		try {// Try a 
			@SuppressWarnings("unused")
			Monster example = new Monster("E", 20,19, 5, 100, 3);
			fail("The name can't be too short");
		}
		catch(StringFormatException e) {
		}
		try {// Try starting the name with a lowercase letter
			@SuppressWarnings("unused")
			Monster example = new Monster("example", 20,19, 5, 100, 3);
			fail("Lowercase start is illegal");
		}
		catch(StringFormatException e) {
		}
		try {// try the legal name
			@SuppressWarnings("unused")
			Monster example = new Monster("Example", 20,19,5,100, 3);
		}
		catch(StringFormatException e) {
			fail("This should be a legal name, something must be wrong in the conditions.");
		}
	}
}