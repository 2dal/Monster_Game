package toolsForMonsters;

public class IsFibonacci {
	//Checks if the number is  a part of the  Fibonacci sequence
	public static boolean Fib(long Number) {
		if (isPerfectSquare(5*Number*Number-4)||isPerfectSquare(5*Number*Number+4)) {
			return true;
		}
		else
			return false;
	}
	public static boolean isPerfectSquare(long Number) {
		long s;
		s= (long) Math.sqrt(Number); // no sqrt for long so we cast it as a long 
		s=s*s;
		if (s==Number)
			return true;
		else 
			return false;
	}
}
