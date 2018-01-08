/**
 * Write a java function that checks if the input string contains all the letters of the alphabet
 * a-z (case-insensitive). Write time and space complexity of your solution as comments in
 * source file.
 */

import java.util.Scanner;

/**
 * @author Balraj Singh
 */
public class JavaAssignment2 {

    /**
     *
     * @param  input It is the input string to be checked.
     * @return true : if all characters are present in input, else false.
     *
     * Time Complexity : O(N) and Space Complexity : O(26)
     * where N is input string length.
     */
    static boolean isAllCharactersPresent(String input) {

        //mark the character present.
        boolean[] isPresent = new boolean[26];
        for(char c : input.toCharArray()) {
            if(c == ' ') continue;
            isPresent[c - 97] = true;
        }
        for(boolean present : isPresent)
            if(!present) return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim().toLowerCase();
        String VALID = "Valid", INVALID = "Invalid";
        boolean allCharacterPresent = isAllCharactersPresent(input);
        System.out.print(allCharacterPresent ? VALID : INVALID);
    }
}
