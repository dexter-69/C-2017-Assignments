/**
 * Using the documentation for java.util.regex.Pattern as a resource, write and test a regular expression that checks a
 * sentence to see that it begins with a capital letter and ends with a period.
 */
/**
 * @author Balraj Singh
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class JavaAssignmentDay5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();
        String regex = "[A-Z]+.*.+";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(pattern.matcher(input).matches() ? "VALID" : "INVALID");
    }
}
