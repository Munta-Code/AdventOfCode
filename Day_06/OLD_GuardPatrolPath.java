/*
 * This is my first unoptimized version of the Part 1 Code
 */
// Part 1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class OLD_GuardPatrolPath {
	
	public static void main(String[] args) {
		int x = -1;
		int y = -1;
		int distinctPositionCounter = 1;
		
		ArrayList<String> temp = null;
		System.out.print("Path to input file: ");
		Scanner scanner = new Scanner(System.in);
		try {
			temp = (ArrayList<String>) Files.readAllLines(Path.of(scanner.nextLine()));
		} catch (IOException e) {
			System.out.println("File not found.");
			System.exit(1);
		}
		scanner.close();
		
		for (int i = 0; i < temp.size(); i++) {
			x = temp.get(i).indexOf("^");
			if (x != -1) {
				y = i;
				break;
			}
		}
		int length = temp.getFirst().length();
		
		ArrayList<char[]> lines = new ArrayList<>();
		for (String s : temp) {
			lines.add(s.toCharArray());
		}
		
		
		while (x != 0 && x != length-1 && y != 0 && y != length-1) {
			switch(lines.get(y)[x]) {
			case '^':
				if (lines.get(y-1)[x] != '#') {
					if (lines.get(y-1)[x] == '.') distinctPositionCounter++;
					lines.get(y-1)[x] = '^';
					lines.get(y)[x] = 'X';
						y--;
				} else {
					lines.get(y)[x] = '>';
				}
				break;
			case '>':
				if (lines.get(y)[x+1] != '#') {
					if (lines.get(y)[x+1] == '.') distinctPositionCounter++;
					lines.get(y)[x+1] = '>';
					lines.get(y)[x] = 'X';
						x++;
				} else {
					lines.get(y)[x] = 'v';
				}
				break;
			case 'v':
				if (lines.get(y+1)[x] != '#') {
					if (lines.get(y+1)[x] == '.') distinctPositionCounter++;
					lines.get(y+1)[x] = 'v';
					lines.get(y)[x] = 'X';
						y++;
				} else {
					lines.get(y)[x] = '<';
				}
				break;
			case '<':
				if (lines.get(y)[x-1] != '#') {
					if (lines.get(y)[x-1] == '.') distinctPositionCounter++;
					lines.get(y)[x-1] = '<';
					lines.get(y)[x] = 'X';
						x--;
				} else {
					lines.get(y)[x] = '^';
				}
			}
		}
		System.out.println("Distinct positions visited: " + distinctPositionCounter);
		
	}
}
