// Part 1

import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;

public class OrderedUpdateFinder {

	public static void main(String[] args) {
		int middlePageSum = 0;
		
		List<String> lines = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Path to input file: ");
		try {
			lines = Files.readAllLines(Path.of(scanner.nextLine()));
		} catch (IOException e) {
			System.out.println("File not found.");
			System.exit(1);
		}
		scanner.close();
		
		
		ArrayList<int[]> orderingRules = new ArrayList<>();
		while (!lines.getFirst().equals("")) {
			int firstNumber = Integer.parseInt(lines.getFirst().substring(0,2));
			int secondNumber = Integer.parseInt(lines.getFirst().substring(3,5));
			int[] rule = {firstNumber, secondNumber};
			orderingRules.add(rule);
			lines.removeFirst();
		}
		lines.removeFirst();
		
		int k = 0;
		while (!lines.isEmpty()) {
			k++;
			String[] update = lines.getFirst().split(",");
			boolean rightOrder = true;
			outer:
			for (int i = 0; i < update.length; i++) {
				for (int j = 0; j < update.length; j++) {
					for (int[] rule : orderingRules) {
						if (i < j && Integer.parseInt(update[i]) == rule[1] && Integer.parseInt(update[j]) == rule[0]) {
							rightOrder = false;
							break outer;
						} else if (i > j && Integer.parseInt(update[i]) == rule[0] && Integer.parseInt(update[j]) == rule[1]) {
							rightOrder = false;
							break outer;
						}
							
					}
				}
			}
			if (rightOrder) {
				middlePageSum += Integer.parseInt(update[update.length/2]);
			}
			lines.removeFirst();
		}
		System.out.println("Sum of correct update middle pages: " + middlePageSum);
	}

}
