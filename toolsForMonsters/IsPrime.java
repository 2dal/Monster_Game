package toolsForMonsters;

public class IsPrime {
		/**
		 * 
		 * Inspector isPrime is a method that tests if a number is a prime number or not. 
		 * 
		 * @param n 
		 * 			|an integer number
		 * @return 	...
		 * 			|if n == prime number 
		 * 			|return true
		 * @return	...
		 * 			|if n =! prime
		 * 			|return false
		 */
		public static boolean Prime(int n) {
		    // fast even test.
		    if(n > 2 && (n & 1) == 0)
		       return false;
		    // only odd factors need to be tested up to n^0.5
		    for(int i = 3; i * i <= n; i += 2)
		        if (n % i == 0) 
		            return false;
		    return true;
	}

}
