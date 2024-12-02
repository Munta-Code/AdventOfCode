package reports;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountSafeReports {
	
	public static void main(String[] args) {
		int safeReportsCounter = 0;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input file path: ");
		String file = scanner.nextLine();
		scanner.close();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String report;
			while ((report = reader.readLine()) != null) {
				String[] reportArr = report.split("\s");
				if (isSafe(reportArr)) {
					safeReportsCounter++;
				}
			}
			System.out.println("Number of safe reports: " + safeReportsCounter);
		}
		
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	
	
	public static boolean isSafe(String[] report) {
		int levelDifference = (int) Math.signum(Integer.parseInt(report[1]) - Integer.parseInt(report[0]));
		for (int i = 1; i < report.length; i++) {
			int nextDifference = levelDifference * (Integer.parseInt(report[i]) - Integer.parseInt(report[i-1])); 
			if (nextDifference < 1 || nextDifference > 3) {
				return false;
			}
		}
		return true;
	}

}