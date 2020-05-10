package StringMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length() > s2.length()) {
			return s1;
		}
		if(s2.length() > s1.length()){
			return s2;
		}
		return "equal";
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		String a = s;
		if(a.contains("underscores")) {
			a = a.replace(' ', '_');
		}
		return a;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		if(lastName(s1).compareTo(lastName(s2)) < 0 && lastName(s1).compareTo(lastName(s3)) < 0) {
			return s1;
		}
		if(lastName(s2).compareTo(lastName(s1)) < 0 && lastName(s2).compareTo(lastName(s3)) < 0) {
			return s2;
		}
		if(lastName(s3).compareTo(lastName(s1)) < 0 && lastName(s3).compareTo(lastName(s2)) < 0) {
			return s3;
		}
		return "equal";
		
	}
	static String lastName(String s) {
		String a = s;
		a = a.trim();
		String[] b = a.split(" ");
		a = b[1];
		a.trim();
		return a;
	}
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int a = 0;
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.toCharArray()[i])) {
				a += Integer.parseInt(Character.toString(s.toCharArray()[i]));
			}
		}
		return a;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < substring.length(); j++) {
				if(substring.charAt(j) != s.charAt(i)) {
					break;
				}
				if(j == substring.length() - 1) {
					count++;
					break;
				}
				i++;
			}
		}
		return count;
	}

	// Call Utilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int count = 0;
		String[] a = s.trim().split(" ");
		for(String b : a) {
			if(b.length() >= substring.length()) {
				for(int i = substring.length() - 1; i >= 0; i--) {
					if(b.charAt(b.length() - substring.length() + i) != substring.charAt(i)) {
						break;
					}
					if(i == 0) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int I1 = -1, I2 = -1;
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < substring.length(); j++) {
				if(s.charAt(i) != substring.charAt(j)) {
					break;
				}
				if(j == substring.length() - 1) {
					I1 = i;
					break;
				}
				i++;
			}
			if(I1 != -1) {
				break;
			}
		}
		for(int i = s.length() - 1; i >= 0; i--) {
			for(int j = substring.length() - 1; j >= 0; j--) {
				if(s.charAt(i) != substring.charAt(j)) {
					break;
				}
				if(j == 0) {
					I2 = i;
					break;
				}
				i--;
			}
			if(I2 != -1) {
				break;
			}
		}
		return (I2 - I1 - 1);
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String purified = "";
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isLetter(s.charAt(i))){
				s = s.replace(s.charAt(i), ' ');
			}
		}
		for(String a : s.split(" ")) {
			purified += a.toLowerCase();
		}
		for(int i = 0; i < purified.length()/2; i++) {
			System.out.println(purified);
			System.out.println(purified.charAt(i) + ", " + purified.charAt(purified.length() - i - 1));
			if(purified.charAt(i) != purified.charAt(purified.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
