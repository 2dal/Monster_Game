package toolsForMonsters;

/**
 * This method creates random numbers of different sorts
 * 
 * @author Aslak Todal
 * 
 *
 * @return a random integer between integer from and integer to
 * 
 */

public class getRandom {
	/**
	 * 
	 * @param from
	 * @param to
	 * 
	 * @return a random integer between from and to
	 */
	public static int getRandomNumber(int from, int to) {
		to++;
		int RandomNumber;
		int number;
		number = to - from;
		RandomNumber=(int )(Math.random()*number);
		RandomNumber=RandomNumber+from;
				return RandomNumber;
	}
	/**
	 * 
	 * @param 	from1
	 * 			The long 
	 * 
	 * @param 	to1
	 * 
	 * @return 	random long integer between from and to
	 */
	public static long getRandomLongNumber(long from1, long to1) {
		to1++;
		long RandomLong;
		long LongNumber;
		LongNumber = to1 - from1;
		RandomLong=(long)(Math.random()*LongNumber);
		RandomLong=RandomLong+from1;
	//	System.out.println(RandomLong);
				return RandomLong;
	}
	/**
	 * 
	 * @return	a random fibonacci number
	 * 			|isFibonacci(result)
	 */
	public static long getRandomFibonacci() {
		long n = getRandomLongNumber(2,92); // the 92th fibonacci number is the highest fibonacci number that can be represented as an integer
		long a=0, b=0, c=1;
		for (int i = 0; i<=n ;i++) {
			a=b;
			b=c;
			c=a+b;
		}
		return c;
	}
}
