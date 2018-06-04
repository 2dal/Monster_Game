package toolsForMonsters;
import be.kuleuven.cs.som.annotate.*;

public class StringFormatException extends Exception {
private final String falseName;

public StringFormatException(String falseName) {
	this.falseName = falseName;
}
/**
 * 
 * @return
 */
@Basic @Immutable
public String getfalseName() {
	return this.falseName;
}
private static final long serialVersionUID = 2003001L; // no idea what this does and if it's right or not
}
