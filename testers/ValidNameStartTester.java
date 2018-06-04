package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;

public class ValidNameStartTester {
	@Test public void IsValidNameStart() throws StringFormatException {
		assertFalse(Monster.isValidNameStart("n"));
		assertFalse(Monster.isValidNameStart("2"));
		assertFalse(Monster.isValidNameStart("%"));
		assertFalse(Monster.isValidNameStart(" "));
		assertFalse(Monster.isValidNameStart("'"));
		assertTrue(Monster.isValidNameStart("N"));
	}
}
