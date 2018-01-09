/**
 * Write a java function that will ping any host ( given as input ) and computes the median
 * of the time taken to ping.
 */

/**
 * @author Balraj Singh
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JavaAssignment3 {
    static String getPingTime(String command) {
        long finishTime = 0;
        long startTime = 0;
        String HOST_UNREACHABLE = "Host Unreachable";
        try {
            startTime = System.currentTimeMillis();
            Process p = Runtime.getRuntime().exec(command);
            finishTime = System.currentTimeMillis();
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            if(null == inputStream.readLine())
                return HOST_UNREACHABLE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(finishTime - startTime) + " ms";
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String host = scan.next();
        String command = "ping -c 1 " + host;
        String pingTime = getPingTime(command);
        System.out.print(pingTime);
    }
}
