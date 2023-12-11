import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n"; // Default delimiters

        if (numbers.startsWith("//")) {
            int delimiterStart = numbers.indexOf("[") + 1;
            int delimiterEnd = numbers.indexOf("]");
            delimiter = numbers.substring(delimiterStart, delimiterEnd);
            numbers = numbers.substring(delimiterEnd + 3); // Skip the custom delimiter and the newline
        }

        String[] numberArray = numbers.split(delimiter);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String num : numberArray) {
            if (!num.isEmpty()) {
                int value = Integer.parseInt(num);
                if (value < 0) {
                    negatives.add(value);
                } else if (value <= 1000) {
                    sum += value;
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        // Test cases
        System.out.println(calculator.add(""));               // Output: 0
        System.out.println(calculator.add("1"));              // Output: 1
        System.out.println(calculator.add("1,2"));            // Output: 3
        System.out.println(calculator.add("1,2,3,4,5"));      // Output: 15
        System.out.println(calculator.add("1\n2,3"));         // Output: 6
        System.out.println(calculator.add("//;\n1;2"));       // Output: 3
        System.out.println(calculator.add("-1,2"));           // Should throw an exception
        System.out.println(calculator.add("2,-4,3,-5"));      // Should throw an exception
        System.out.println(calculator.add("1001,2"));         // Output: 2
        System.out.println(calculator.add("//[|||]\n1|||2|||3")); // Output: 6
    }
}
