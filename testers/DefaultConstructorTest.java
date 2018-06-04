package testers;
import org.junit.Test;
import monsterPackage.Monster;
import toolsForMonsters.StringFormatException;
import static org.junit.Assert.*;

 public class DefaultConstructorTest{
	@Test public void singleCase() throws Exception, StringFormatException {
		Monster reference = new Monster();
		Monster monster = new Monster("DestroyerO'hope", reference.getMaxDamage()+200, 20, 19, reference.getMaxHitpoint(), 21);
		assertEquals(monster.getDamage(),monster.getMaxDamage());
		assertEquals(monster.getHitpoint(),monster.getMaxHitpoint());
		
	}
}
