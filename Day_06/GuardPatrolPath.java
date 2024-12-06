/*
 * I followed ChatGPTs advice to make my code shorter
 */
// Part 1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class GuardPatrolPath {
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) {
		int y = -1;
		int x = -1;
		int facing = 0;
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
		ArrayList<String[]> lines = new ArrayList<>();
		for (String s : temp) {
			lines.add(s.split(""));
		}
		
		
		for (int i = 0; i < temp.size(); i++) {
			x = temp.get(i).indexOf("^");
			if (x != -1) {
				y = i;
				break;
			}
		}
		
		while (x != 0 && x != lines.getFirst().length-1 && y != 0 && y != lines.size()-1) {
			if (!lines.get(y+dir[facing][0])[x+dir[facing][1]].equals("#")) {
				if (!lines.get(y+dir[facing][0])[x+dir[facing][1]].equals("X")) distinctPositionCounter++;
				lines.get(y)[x] = "X";
				y += dir[facing][0];
				x += dir[facing][1];
			} else {
				facing = (facing + 1) % 4;
			}
		}
		System.out.println(distinctPositionCounter);
	}
	
}
