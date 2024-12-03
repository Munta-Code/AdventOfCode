// Part 2

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SumOfEnabledMultInstructions {
	
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
		
		Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)");
		Matcher matcher = pattern.matcher(sb);
		boolean doMult = true;
		while (matcher.find()) {
			if (matcher.group(0).equals("do()")) {
				doMult = true;
			} else if (matcher.group(0).equals("don't()")) {
				doMult = false;
			} else if (doMult){
				multSum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
			}
		}
		System.out.println("Sum of all enabled real multiplication instructions: " + multSum);
	}

}
