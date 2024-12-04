// Part 1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmasFinder {

	public static void main(String[] args) {
		int xmasCounter = 0;
		
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
		
		int length = lines.getFirst().length();
		
		
		ArrayList<List<String>> allLists = new ArrayList<>();
		allLists.add(lines);
		
		// Create 3 more lists to have each orientation
		ArrayList<String> columns = new ArrayList<>();
		for (int i = length-1; i >= 0; i--) {
			StringBuilder sb = new StringBuilder();
			for (String s: lines) {
				sb.append(s.charAt(i));
			}
			columns.add(sb.toString());
		}
		allLists.add(columns);
		
		ArrayList<String> verticalRight = new ArrayList<>();
		for (int i = 0; i <= lines.size()+length-8; i++) {
			int k = Math.max(0, i-(length-4));
			StringBuilder sb = new StringBuilder();
			for (int j = length-4-Math.min(i, length-4); j < length && k < lines.size(); j++) {
				sb.append(lines.get(k).charAt(j));
				k++;
			}
			verticalRight.add(sb.toString());
		}
		allLists.add(verticalRight);
		
		ArrayList<String> verticalLeft = new ArrayList<>();
		for (int i = 0; i <= columns.size()+lines.size()-8; i++) {
			int k = Math.max(0, i-(lines.size()-4));
			StringBuilder sb = new StringBuilder();
			for (int j = lines.size()-4-Math.min(i, lines.size()-4); j < lines.size() && k < columns.size(); j++) {
				sb.append(columns.get(k).charAt(j));
				k++;
			}
			verticalLeft.add(sb.toString());
		}
		allLists.add(verticalLeft);
		
		// Find "XMAS" and "SAMX"
		Pattern pattern = Pattern.compile("(?=XMAS)|(?=SAMX)");
		for (List<String> list : allLists) {  
			for (String s : list) {
				Matcher matcher = pattern.matcher(s);
				while (matcher.find()) {
					xmasCounter++;
				}
			}
		}
		System.out.println(xmasCounter);
		
	}	
}
