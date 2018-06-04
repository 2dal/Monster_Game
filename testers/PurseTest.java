package testers;
import static org.junit.Assert.*;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;

import org.junit.Test;
import monsterPackage.*;

public class PurseTest {
	@Test public void isValidCapacity() {
		Purse overflow = new Purse();
		overflow.addDukats((int) (overflow.getCapacity()+1));
		assertTrue(overflow.getDukats()==0);
		try {
		overflow.addDukats(1);
		}
		catch (IllegalStateException e) {
			
		}
		assertTrue(overflow.getDukats()==0);
		
		
		Purse remove = new Purse();
		remove.addDukats((int) remove.getCapacity());
		assertEquals(remove.getDukats(),remove.getCapacity());
		remove.removeDukats(remove.getCapacity());
		assertTrue(remove.getDukats()==0);
		
		try {
			@SuppressWarnings("unused")
			Purse negative= new Purse(1,1,-1, 0);
			fail("Lol iuno");
		}
		catch(Exception e){
			
		}
	}
}
