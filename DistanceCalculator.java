package lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DistanceCalculator {
	
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
		Collections.sort(list1);
		Collections.sort(list2);
		
		int distance = 0;
		for (int i = 0; i < list1.size(); i++) {
			distance += Math.abs(list1.get(i) - list2.get(i));
		}
		
		System.out.println(distance);
	}
}
