package encapsulation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Person {
    private String name;
    private String email;
    private Date birthday;
    private String ssn;
    private char gender;
    private List<String> validCTLDs = Arrays.asList("ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw");

    private boolean isAlpha(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isLetter(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCountry(String tld) {
        return (tld.length() == 2 || tld.length() == 3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String[] names = name.split("\\s+");
        if (names.length != 2) {
            throw new IllegalArgumentException("Please input both your first and your last name, and remove middle names.");
        }
        else if (!((isAlpha(names[0])) && (isAlpha(names[1])))) {
            throw new IllegalArgumentException("Your name may only contain letters.");
        }
        else if (names[0].length() < 2 || names[1].length() < 2) {
            throw new IllegalArgumentException("Your names have to be longer than 2 chars.");
        }
        else {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String[] emailSplit = email.split("@");
        String[] domain;
        int numOfSubDomains;

        String[] emailNames;

        try {
            domain = emailSplit[1].split("\\.");
            numOfSubDomains = domain.length;

            emailNames = emailSplit[0].split("\\.");
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Names or e-mail are not valid");
        }

        String name = getName();
        String firstName = name.split("\\s+")[0].toLowerCase();
        String lastName = name.split("\\s+")[0].toLowerCase();

        if (emailSplit.length != 2 && !(emailSplit.length == 0)) {
            throw new IllegalArgumentException("Please make sure you typed in your email address correctly. [Error: Multiple @'s]");
        }
        else if (! validCTLDs.contains(domain[1])){
			throw new IllegalArgumentException("The country code " + domain[1] + " is not a valid top level domain");
		}
		if (domain.length != 2) {
			throw new IllegalArgumentException("The domain part should include two words separated with .");
		}
        else if (!(isCountry(domain[numOfSubDomains-1])) || !(isAlpha(domain[numOfSubDomains-1]))) {
            throw new IllegalArgumentException("Please use a valid country code.");
        }
        else if (!(emailNames[0].toLowerCase().equals(firstName)) && !(emailNames[1].toLowerCase().equals(lastName))) {
            throw new IllegalStateException("Name is not valid");
        }
        else {
            this.email = email;
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
		if (! birthday.before(new Date())) {
			throw new IllegalArgumentException(birthday + " is in the future");
		}
		this.birthday = birthday;						
	}

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender == 'M' || gender == 'F' || gender == '\0') {
            this.gender = gender;
        }
        else {
            throw new IllegalArgumentException("Gender is either [M]ale or [F]emale.");
        }
    }
    private static int[]
			factors1 = {3, 7, 6, 1, 8, 9, 4, 5, 2},
			factors2 = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2}; 
	 
	private static int computeControlDigit(String digits, int[] factors) { 
		int sum = 0; 
		for (int i = 0; i < factors.length; i++) { 
			sum += (digits.charAt(i) - '0') * factors[i]; 
		} 
		sum = 11 - (sum % 11);
		return sum == 11 ? 0 : sum; 
	} 
	 
	private static boolean checkDigits(String digits, int pos, int num) { 
		return (num / 10 == digits.charAt(pos) - '0' && num % 10 == digits.charAt(pos + 1) - '0'); 
	} 
	 
	@SuppressWarnings("deprecation")
	private boolean validateSSN(String pid) { 
		if (pid.length() != 11) { 
			return false; 
		} 
		for (int i = 0; i < pid.length(); i++) { 
			if (! Character.isDigit(pid.charAt(i))) { 
				return false; 
			} 
		}
		int day = birthday.getDate();
		int month = birthday.getMonth()+1;
		int year = birthday.getYear() + 1900;
		
		if (! (checkDigits(pid, 0, day) && checkDigits(pid, 2, month)) && checkDigits(pid, 4, year)) { 
			return false; 
		} 
		boolean isOdd = ((pid.charAt(8) - '0') % 2) == 1; 
		if ((gender == 'M') != isOdd) { 
			return false; 
		} 
		int k1 = computeControlDigit(pid, factors1), k2 = computeControlDigit(pid, factors2); 
		if (k1 != pid.charAt(9) - '0' || k2 != pid.charAt(10) - '0') { 
			return false; 
		} 
		return true; 
	} 
	 
	public String getSSN() { 
		 return ssn;
	} 
	 
	public void setSSN(String ssn) { 
		if (! validateSSN(ssn)) { 
			throw new IllegalArgumentException(ssn + " is not a valid PID for " + gender + " and " + birthday); 
		} 
		this.ssn = ssn; 
	}
}