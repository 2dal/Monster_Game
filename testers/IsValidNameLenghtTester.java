package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;

public class IsValidNameLenghtTester {
	@Test public void IsValidNameLenghtValid() throws StringFormatException {
		assertTrue(Monster.isValidNameLenght("1234567"));
		assertTrue(Monster.isValidNameLenght("123456"));
		assertTrue(Monster.isValidNameLenght("12345"));
		assertTrue(Monster.isValidNameLenght("1234"));
		assertTrue(Monster.isValidNameLenght("123"));
		assertTrue(Monster.isValidNameLenght("12"));
		assertFalse(Monster.isValidNameLenght("1"));
		assertFalse(Monster.isValidNameLenght(""));
	}
}
