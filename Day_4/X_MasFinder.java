// Part 3

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class X_MasFinder {

	public static void main(String[] args) {
		int xMasCounter = 0;
		
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
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < length; j++) {
				if (lines.get(i).charAt(j) == 'A') {
					try {
						if ((lines.get(i-1).charAt(j-1) == 'M' && lines.get(i+1).charAt(j+1) == 'S')
								|| (lines.get(i-1).charAt(j-1) == 'S' && lines.get(i+1).charAt(j+1) == 'M')) {
							if ((lines.get(i-1).charAt(j+1) == 'M' && lines.get(i+1).charAt(j-1) == 'S')
									|| (lines.get(i-1).charAt(j+1) == 'S' && lines.get(i+1).charAt(j-1) == 'M')) {
								xMasCounter++;
							}
						}
					} catch (IndexOutOfBoundsException e) {}
				}
			}
		}
		System.out.println("Number of occurences of an X-MAS: " + xMasCounter);
			
	}
}
