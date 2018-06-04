package testers;
import static org.junit.Assert.*;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;

public class SetDamageTester {
/**
 * This tester will try to give a monster too low and too high damage. 
 * Since damage is worked out totally it will correct itself to the nearest legal number, which is 1 for min and maxDamage() for max
 * the tester will also check that strength setter works normally by giving a monster half of the max legal strength and check if the value was used.
 * @throws StringFormatException
 */
	 @Test public void testDamagesetter() throws StringFormatException {
			Monster example = new Monster("Example",1,19, 5, 100, 3); //Example is created to have a easy access to maxDamage
			
			
			int maxs = example.getMaxDamage()+1;// maxs will be 1 higher than the allowed max damage
			Monster tooStrong= new Monster("Strongman", maxs+1,19,5,example.getMaxHitpoint(), 3);
			assertEquals(tooStrong.getDamage(),example.getMaxDamage());
			
			
			int mins = 0;// mins will be 1 lower than the allowed min damage
			Monster tooWeak = new Monster("Weakboi", mins-1, 19,5,example.getMaxHitpoint(), 3);
			assertEquals(tooWeak.getDamage(),1);
			
			int leagal = example.getMaxDamage()/2; // half the maxDamage will always be leagal 
			Monster Leagal_boy = new Monster("Normal",leagal, 19,5, example.getMaxHitpoint(), 3);
			assertEquals(Leagal_boy.getDamage(),leagal);
	 }
}
