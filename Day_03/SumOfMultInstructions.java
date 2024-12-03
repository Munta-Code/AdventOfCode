// Part 1

import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SumOfMultInstructions {

	public static void main(String[] args) {
		int multSum = 0;
		
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
		
		StringBuilder sb = new StringBuilder();
		for (String s: lines) {
			sb.append(s);
		}
		
		Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
		Matcher matcher = pattern.matcher(sb);
		while (matcher.find()) {
			multSum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
		}
		System.out.println("Sum of all real multiplication instructions: " + multSum);
	}

}
