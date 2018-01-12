/**
 * Using TextFile and a Map<Character,Integer>, create a program that takes the file name as a
 * command line argument and counts the occurrence of all the different characters.
 * Save the results in a text file.
 */

/**
 * @author Balraj Singh
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaAssignmentDay8 {
    //Remember to pass absolute file path via CLI Arguments
    public static void main(String[] args) throws IOException {

        if(null == args) {
            System.out.println("Pass Absolute File Path Via Arguments");
            System.exit(0);
        }

        String fileName = args[0];

        //Stores count of each character
        Map<Character, Integer> countMap = new HashMap<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);

            bufferedReader = new BufferedReader(fileReader);
            String line;

            while (null != (line = bufferedReader.readLine())) {
                for (char c : line.toCharArray())
                    countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //just change the username 'zuser' to point to your desktop
        File newFile = new File("/home/zuser/Desktop/output.txt");
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        if (newFile.createNewFile()) {
            try {
                writer = new FileWriter(newFile);
                bufferedWriter = new BufferedWriter(writer);
                Set<Character> keySet = countMap.keySet();
                for (Character key : keySet) {
                    bufferedWriter.write(key + " " + countMap.get(key));
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedWriter.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
