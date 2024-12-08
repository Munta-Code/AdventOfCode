// Got some help from ChatGPT
// Part 1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CalibrationResultCalculator {

    public static void main(String[] args) {
        long calibrationSum = 0;

        ArrayList<String> lines = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Path to input file: ");
        try {
            lines = (ArrayList<String>) Files.readAllLines(Path.of(scanner.nextLine()));
        } catch (IOException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        scanner.close();

        for (String line : lines) {
            line = line.replace(":", "");
            String[] temp = line.split(" ");
            long result = Long.parseLong(temp[0]);
            int[] nums = new int[temp.length-1];
            for (int i = 1; i < temp.length; i++) {
                nums[i-1] = Integer.parseInt(temp[i]);
            }

            int combinations = (int) Math.pow(2, nums.length - 1);

            for (int mask = 0; mask < combinations; mask++) {
                long calc = nums[0];

                for (int i = 0; i < nums.length - 1; i++) {
                    if ((mask & (1 << i)) == 0) {
                        calc += nums[i + 1];
                    } else {
                        calc *= nums[i + 1];
                    }
                }

                if (calc == result) {
                    calibrationSum += result;
                    break;
                }
            }
        }

        System.out.println(calibrationSum);
    }
}
