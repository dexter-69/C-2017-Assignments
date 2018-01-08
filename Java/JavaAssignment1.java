
/**
 * Create an java program to search through the home directory and look for files that match a
 * regular expression. The program should be able to take inputs repeatedly and should print out
 * full absolute path of the matching files found.
 */

/**
 * @author Balraj Singh
 */

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;


public class JavaAssignment1 {

    /**
     * This method search files recursively in directory and its sub-directories
     * @param pattern this is regex pattern to be matched with file names.
     * @param directoryName this is the current directory we're searching in.
     *
     */
    static void findFiles(String pattern, String directoryName) {

        //Get to the directoryName
        File directory = new File(directoryName);
        final Pattern regexPattern;
        try {
            regexPattern = Pattern.compile(pattern);
        } catch (Exception e) {
            System.out.println("Please Enter Valid Java Regex");
            return;
        }

        //list all files in the directory
        File[] files = directory.listFiles();
        if (null == files) return;

        for (File file : files) {
            if (file.isFile()) {
                if (regexPattern.matcher(file.getName()).matches())
                    System.out.println(file.getAbsolutePath());
            }

            //Search in the subdirectory
            else if (file.isDirectory())
                findFiles(pattern, file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Regex or -1 to quit");
        String HALT = "-1";
        String pattern = scan.next();
        String homeDirectory = "/home/zuser";

        //Run un-till user enters -1
        while (!pattern.equals(HALT)) {
            findFiles(pattern, homeDirectory);
            System.out.println("\nEnter Next Regex or -1 to quit");
            pattern = scan.next();
        }
    }
}

