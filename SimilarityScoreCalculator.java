package lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimilarityScoreCalculator {

	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		
		List<String> lines = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Path to input file: ");
		try {
			lines = Files.readAllLines(Path.of(scanner.nextLine()));
		} catch (IOException e) {
			System.out.println("File not found.");
			System.exit(1);
		}
		scanner.close();
		
		for (String s : lines) {
			String[] arr = s.split("   ");
			list1.add(Integer.parseInt(arr[0]));
			list2.add(Integer.parseInt(arr[1]));
		}
		
		int similarityScore = 0;
		for (int i : list1) {
			int occurrences = 0;
			for (int j : list2) {
				if (i == j) {
					occurrences++;
				}
			}
			similarityScore += i * occurrences;
		}
		
		System.out.println(similarityScore);
	}
}