package testers;
import static org.junit.Assert.*;
import org.junit.Test;
//import monsterPackage.Monster;
import toolsForMonsters.*;

public class FiboTester {
	int i;
	/**
	 * 
	 */
	@Test public void Fibstester() {
		long fibonacci[] = new long[20];
		fibonacci[0]=1;
		fibonacci[1]=1;
		for ( i=2;i<fibonacci.length;i++) {
			fibonacci[i]=fibonacci[i-1]+fibonacci[i-2];
		}
		for (int k = 0; k < fibonacci.length; k++) {
			long j = fibonacci[k];
			assertTrue(IsFibonacci.Fib(j));
		}
		assertFalse(IsFibonacci.Fib(4));
		assertFalse(IsFibonacci.Fib(6));
		assertFalse(IsFibonacci.Fib(7));
		assertFalse(IsFibonacci.Fib(10));
		assertFalse(IsFibonacci.Fib(300));
	}
}
