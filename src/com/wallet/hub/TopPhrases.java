package com.wallet.hub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Class with methods to search large pipe "|" delimited file for top phrases
 * 
 * @author denisb
 *
 */
public class TopPhrases {

	/**
	 * This method parses a file for a set of phrases delimited by the pipe
	 * character
	 * 
	 * @param filePath
	 *            This is the path of the file
	 * 
	 * @return HashMap<String, Integer> The key is the phrase and the value is
	 *         the number of occurences
	 * 
	 * @throws IOException
	 */
	public static HashMap<String, Integer> scanLargeFileForPhrases(String filePath) throws IOException {

		FileInputStream inputStream = null;
		// Scanner is used to parse large files as it does not store each line
		// which is read
		Scanner sc = null;
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		try {

			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream, "UTF-8");

			while (sc.hasNextLine()) {

				String line = sc.nextLine();

				// Notice the regex :-)
				String[] phrases = line.split("\\|");

				for (String phrase : phrases) {

					System.out.println(phrase);

					String key = phrase.trim();

					if (hashMap.containsKey(key)) {

						Integer count = hashMap.get(key);

						count = count + 1;

						hashMap.put(key, count);

					} else {
						hashMap.put(key, 1);
					}
				}
			}

			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

		return hashMap;
	}

	/**
	 * This method is used to sort a hasmap in descending order by value
	 * 
	 * @param unSortedHashMap
	 *            this is the unsorted HashMap
	 * 
	 * @return HashMap<String, Integer> this is the sorted hash map
	 */
	private static HashMap<String, Integer> sortPhrasesInDescendingOrder(HashMap<String, Integer> unSortedHashMap) {

		HashMap<String, Integer> sortedHashMap = new HashMap<String, Integer>();

		// Sort phrases by number of occurrences in descending order using Java
		// 8 lambda expression
		unSortedHashMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.forEachOrdered(x -> sortedHashMap.put(x.getKey(), x.getValue()));

		return sortedHashMap;

	}

	/**
	 * @param topValue
	 *            number of top phrases to output
	 * 
	 * @param phrasesAndOccuranceMap
	 */
	public static void getTopPhrases(Integer topValue, HashMap<String, Integer> phrasesAndOccuranceMap) {

		Iterator it = null;
		int count = 0;

		HashMap<String, Integer> sortedHashMap = sortPhrasesInDescendingOrder(phrasesAndOccuranceMap);

		it = sortedHashMap.entrySet().iterator();

		while (it.hasNext()) {

			if (count == topValue) {
				break;
			}

			count++;

			Map.Entry<String, Integer> phraseAndCount = (Map.Entry<String, Integer>) it.next();

			// Output top phrases and occurrences
			System.out
					.println("Phrase: " + phraseAndCount.getKey() + " Occured " + phraseAndCount.getValue() + " Times");

		}
	}

	public static void main(String[] args) {

		try {

			// Create file
			PrintWriter writer = new PrintWriter("TopPhrases.txt", "UTF-8");
			writer.println("Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing |"
					+ " Microsoft Bing | Microsoft Bing | Microsoft Bing " + "| Microsoft Bing | Microsoft Bing");
			writer.close();

			File file = new File("TopPhrases.txt");

			// Get top 10 phrases from file
			HashMap<String, Integer> phrasesAndOccurance = scanLargeFileForPhrases(file.getAbsolutePath());
			getTopPhrases(10, phrasesAndOccurance);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
