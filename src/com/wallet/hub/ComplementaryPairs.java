package com.wallet.hub;

/**
 * Class with methods to check for K-complimentary pairs
 * 
 * @author denisb
 *
 */
public class ComplementaryPairs {

	/**
	 * This method is used to check for K-complimentary pairs
	 * 
	 * @param int[]
	 *            This is an array of integers
	 * 
	 * @param int
	 *            This is K-compliment
	 * 
	 * @return int This is the amount of K-complimentary pairs
	 */
	public int findComplementaryPairs(int arr[], int k) {

		int result = 0;
		for (int i = 0; i <= arr.length; i++) {
			for (int j = i; j < arr.length - 1; j++) {
				if (arr[i] + arr[j + 1] == k) {
					result++;
				}
			}
		}
		return result * 2;
	}

	public static void main(String[] args) {
		int[] intArray = { 4, 5, 6, 3, 1, 8, -7, -6 };
		int k = 1;
		System.out.println(
				"No of k complementary pairs : " + new ComplementaryPairs().findComplementaryPairs(intArray, k));
	}

}
