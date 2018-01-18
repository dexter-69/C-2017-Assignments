/**
 * @author Balraj Singh
 */

import java.util.GregorianCalendar;
import java.util.Scanner;

//This class holds the date and handle the input format
class DateHolder {
    private int date, month, year;
    private String inputDate;

    //Month has 31 or 30 days or 28?
    final static int[] dateCount = new int[]{
        -1, 1, -2, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1
    };
    DateHolder(String inputDate) {
        this.inputDate = inputDate;
        this.date = Integer.parseInt(inputDate.substring(0, 2));
        this.month = Integer.parseInt(inputDate.substring(3, 5));
        this.year = Integer.parseInt(inputDate.substring(6));
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String formatInputDate() {
        StringBuilder builder = new StringBuilder();
        if(date < 10) builder.append('0');
        builder.append(date).append('-');

        if(month < 10) builder.append('0');
        builder.append(month).append('-');
        builder.append(String.valueOf(year));
        return builder.toString();
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
public class JavaAssignment4 {

    //checks the date validity
    private static boolean isInvalidDateRange(DateHolder signUpDate, DateHolder currentDate) {
        if(signUpDate.getYear() < currentDate.getYear()) return false;
        if(signUpDate.getMonth() < currentDate.getMonth()) return false;
        if((signUpDate.getDate() >= currentDate.getDate())
                && ((currentDate.getMonth() <= signUpDate.getMonth())
                || (currentDate.getYear() <= signUpDate.getYear())))
            return true;
        if((signUpDate.getDate() < currentDate.getDate())
                && ((currentDate.getMonth() <= signUpDate.getMonth())
                || (currentDate.getYear() <= signUpDate.getYear())))
            return true;

        return false;
    }

    //This is core function that determines the date range
    private static String getKycDateRange(DateHolder signUpDate, DateHolder currentDate) {
        if(isInvalidDateRange(signUpDate, currentDate))
            return "No range";

        //calendar is used to determine leap year
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        boolean leapYear = calendar.isLeapYear(currentDate.getYear());

        DateHolder minDate = new DateHolder("00-00-" + currentDate.getYear());
        DateHolder maxDate = new DateHolder("00-00-" + currentDate.getYear());

        minDate.setDate(signUpDate.getDate() + DateHolder.dateCount[signUpDate.getMonth() - 1]);
        minDate.setMonth(signUpDate.getMonth() - 1);

        //if sign-up month is january go back to previous year
        if(minDate.getMonth() < 1) {
            minDate.setMonth(12 + minDate.getMonth());
            minDate.setYear(minDate.getYear() - 1);
        }

        //if leap year add one day
        if(leapYear) {
            minDate.setDate(signUpDate.getDate() + 1);
        }
        String newDate = minDate.formatInputDate();
        minDate.setInputDate(newDate);

        maxDate.setDate(signUpDate.getDate() - DateHolder.dateCount[minDate.getMonth()]);
        maxDate.setMonth(signUpDate.getMonth() + 1);

        //handle leap year
        if(signUpDate.getMonth() == 2) {
            int addDay;
            if(leapYear) addDay = 1;
            else addDay = 2;
            maxDate.setDate(signUpDate.getDate() + addDay);
        }
        String newMaxDate = maxDate.formatInputDate();
        maxDate.setInputDate(newMaxDate);

        //check if new max date is in future, then roll back to current date
        if(maxDate.getMonth() > currentDate.getMonth()) {
            maxDate.setInputDate(currentDate.getInputDate());
        } else if(maxDate.getMonth() == currentDate.getMonth()) {
            if(maxDate.getDate() > currentDate.getDate()) {
                maxDate.setInputDate(currentDate.getInputDate());
            }
        }
        return minDate.getInputDate() + " " + maxDate.getInputDate();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t --> 0) {
            DateHolder signUpDate = new DateHolder(scan.next());
            DateHolder currentDate = new DateHolder(scan.next());
            String newRange = getKycDateRange(signUpDate, currentDate);
            System.out.println(newRange);
        }
    }
}
