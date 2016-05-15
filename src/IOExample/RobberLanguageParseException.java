package IOExample;

public class RobberLanguageParseException extends IllegalArgumentException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RobberLanguageParseException(String message, char ch) {
		super(message);
		System.err.println("this is not correct RobberLanguage: " + ch);
	}
 }
