// Part 2

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CausePatrolLoop {
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) {
		int y = -1;
		int x = -1;
		int LoopCausingObstruction = 0;
		
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
		int firstY = y;
		int firstX = x;
		int facing = 0;
		
		ArrayList<String[]> lines = new ArrayList<>();
		for (String s : temp) {
			lines.add(s.split(""));
		}
		
		
		while (x != 0 && x != lines.getFirst().length-1 && y != 0 && y != lines.size()-1) {
			if (!lines.get(y+dir[facing][0])[x+dir[facing][1]].equals("#")) {
				if (!lines.get(y+dir[facing][0])[x+dir[facing][1]].equals("X")) {
					ArrayList<String[]> copy = new ArrayList<>();
					lines.forEach(line -> copy.add(line.clone()));
					copy.get(y+dir[facing][0])[x+dir[facing][1]] = "#";
					copy.get(firstY)[firstX] = "^";
					if (createsLoop(copy, firstX, firstY)) LoopCausingObstruction++;
				}
				lines.get(y)[x] = "X";
				y += dir[facing][0];
				x += dir[facing][1];
			} else {
				facing = (facing + 1) % 4;
			}
		}
		System.out.println("Number of positions that would cause an infinite loop, if you add an obstruction: " + LoopCausingObstruction); // This line is way too long, and this comment is making it even longer, oh no
	}
	
	public static boolean createsLoop(ArrayList<String[]> lines, int x, int y) {
		int facing = 0;
		while (x != 0 && x != lines.getFirst().length-1 && y != 0 && y != lines.size()-1) {
			if (!lines.get(y+dir[facing][0])[x+dir[facing][1]].equals("#")) {
				if (lines.get(y+dir[facing][0])[x+dir[facing][1]].contains("" + facing)) return true;
				lines.get(y)[x] += facing;
				y += dir[facing][0];
				x += dir[facing][1];
			} else {
				facing = (facing + 1) % 4;
			}
		}
		return false;
	}
	
}
