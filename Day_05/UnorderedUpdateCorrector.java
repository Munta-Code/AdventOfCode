// Part 2

import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;

public class UnorderedUpdateCorrector {

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

		while (!lines.isEmpty()) {
			ArrayList<Integer> update = new ArrayList<>();
			for (String page : lines.getFirst().split(",")) {
				update.add(Integer.parseInt(page));
			}
			boolean gotCorrected = false;
			for (int i = 0; i < update.size(); i++) {
				for (int j = 0; j < update.size(); j++) {
					int firstNumber = update.get(i);
					int secondNumber = update.get(j);
					for (int[] rule : orderingRules) {
						if (i < j && firstNumber == rule[1] && secondNumber == rule[0]) {
							gotCorrected = true;
							update.remove((Integer) secondNumber);
							update.add(update.indexOf(firstNumber) + 1, secondNumber);
						} else if (i > j && firstNumber == rule[0] && secondNumber == rule[1]) {
							gotCorrected = true;
							update.remove((Integer) firstNumber);
							update.add(update.indexOf(secondNumber), firstNumber);
						}
					}
				}
			}
			if (gotCorrected) {
				middlePageSum += update.get(update.size() / 2);
			}
			lines.removeFirst();
		}
		System.out.println("Sum of corrected update middle pages: " + middlePageSum);
	}

}
