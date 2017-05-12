package com.wallet.hub;

/**
 * Class with method to check if string is a plaindrome
 * 
 * @author denisb
 *
 */
public class Palindrome {

	/**
	 * This method is used to determine if a string is palindrome.
	 * 
	 * @param String
	 *            This is the first parameter to addNum method.
	 * 
	 * @return Boolean this indicates if a string is a palindrome.
	 */
	public static boolean checkifStringIsAPalindrome(String potentialPalindrome) {

		boolean isPalindrome = false;

		String reversedString = new StringBuilder(potentialPalindrome).reverse().toString();

		if (potentialPalindrome.equals(reversedString)) {
			isPalindrome = true;
		}

		return isPalindrome;
	}

	public static void main(String[] args) {

		String[] words = { "WOW", "Tomorrow", "TEST", "Insane" };

		String[] messages = { "Not a plaindrome ", "Is a palindrome" };

		for (String word : words) {

			String message = Palindrome.checkifStringIsAPalindrome(word) ? messages[1] : messages[0];

			System.out.println(word + " " + message);
		}
	}

}
