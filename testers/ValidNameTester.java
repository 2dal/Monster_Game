package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
//import toolsForMonsters.StringFormatException;
import toolsForMonsters.StringFormatException;

public class ValidNameTester {

	@Test public void validNameTester() throws IllegalArgumentException, StringFormatException{
		assertTrue(Monster.isValidName("abcdefghijklmnopqrstuvwxyz1234567890'  ")); // all the leagal letters a name can have
		assertFalse(Monster.isValidName("%"));
		assertFalse(Monster.isValidName("-"));
	}
}
