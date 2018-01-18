/**
 * Write a java function that will ping any host ( given as input ) and computes the median
 * of the time taken to ping.
 */

/**
 * @author Balraj Singh
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class JavaAssignment3 {

    static double getPingTime(String command) {
        String HOST_UNREACHABLE = "Host Unreachable";
        List<Double> pingTimeList = new ArrayList<>();
        System.out.print("Calculation Ping Time ..");
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            if(null == inputStream.readLine()) {
                System.out.println(HOST_UNREACHABLE);
                return 0.0;
            }
            String s;
            // reading output stream of the command
            while ((s = inputStream.readLine()) != null) {
                System.out.print(".");
                int timeIndex = s.indexOf("time=");
                if(timeIndex == -1) continue;

                String time = s.substring(timeIndex, s.indexOf("ms")).trim();
                double pingTime = Double.valueOf(time.substring(5));

                //System.out.println(pingTime);
                pingTimeList.add(pingTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        Collections.sort(pingTimeList);
        int listSize = pingTimeList.size();
        if(listSize == 0) {
            System.out.println("Ping / Host Error");
            return 0.0;
        }
        if(listSize % 2 != 0) return pingTimeList.get(listSize / 2);

        return mean(pingTimeList, listSize);
    }

    private static double mean(List<Double> list, int size) {
        return (list.get((size - 1) / 2) + list.get(size / 2)) / 2.0;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Host");
        String host = scan.next();

        System.out.println("Enter ping count");
        int pingCount = scan.nextInt();

        if(pingCount <= 0) {
            System.out.println("Please Enter Ping Count > 0...exiting");
            System.exit(0);
        }
        String command = "ping -c " + pingCount + " " + host;
        Double medianPingTime = getPingTime(command);
        System.out.print("Median Ping Time is : " + medianPingTime);
    }
}
