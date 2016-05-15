package IOExample;

import java.io.IOException;

public class RobberLanguage {
	
	private static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";
	private static final String ILLEGAL_CHARS = "#@$§";
	
	public String encryptInRobberLanguage(String inString) {
		String outString = "";
		for (int i = 0; i < inString.length(); i++) {
			char ch = inString.charAt(i);
			
			if (ILLEGAL_CHARS.indexOf(ch) >= 0) {
				throw new RobberLanguageParseException("Invalid Char",ch);
			}
			else if (CONSONANTS.indexOf(Character.toUpperCase(ch)) >= 0) {
				outString += ch + "o" + Character.toLowerCase(ch);
			}
			else {
				outString += ch;
			}
		}
		return outString;
	}
	
	public static void main(String[] args) throws IOException {
		
		RobberLanguage rl = new RobberLanguage();
		System.out.println(rl.encryptInRobberLanguage("java"));
		String homedir = System.getProperty("user.home");
		String filePath = homedir + "/File.txt";
		FileManager fm = new FileManager();
		char[] chars = FileManager.readChars(filePath);
		String fileContent = new String(chars);
		System.out.println("Input from reader: " + fileContent.toString());
		
		String encryptedContent = rl.encryptInRobberLanguage(fileContent);
		System.out.println(encryptedContent);
		
		String encryptedFilePath = homedir + "/encFile.txt";
		fm.writeString(encryptedContent,encryptedFilePath);
		System.out.println(homedir);
		System.out.println(System.getProperty("user.name"));
	}

}
