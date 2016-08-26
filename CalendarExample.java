import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Alex on 20.08.2016.
 * This program counts the number of weekends between two dates
 */
public class CalendarExample {

    private static Calendar calendarStartDay, calendarEndDay ;

    public static void main(String[] args) {

        calendarStartDay = new GregorianCalendar();
        calendarEndDay = new GregorianCalendar();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate, endDate;
        long startTime;
        long endTime;
        int amountOfDays = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter start date in format: dd.MM.yyyy!");
            String startDateInString= reader.readLine();

            System.out.println("Enter end date in format: dd.MM.yyyy!");
            String endDateInString = reader.readLine();

            try {

                startDate = simpleDateFormat.parse(startDateInString);
                simpleDateFormat.setLenient(false);
                endDate = simpleDateFormat.parse(endDateInString);

                if (startDate.getTime() < endDate.getTime()) {
                    startTime = startDate.getTime();
                    endTime = endDate.getTime();
                    amountOfDays =(int)((endTime - startTime)/1000/24/60/60) + 1;
                    calendarStartDay.setTime(startDate);
                    calendarEndDay.setTime(endDate);
                }
                else {
                    System.out.println("The end date should be more than start date");
                }
            }
            catch (ParseException e) {
                System.out.println("Incorect data! Try again!");
                e.printStackTrace();
                return;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        countWeekends(amountOfDays);
    }

    public static void countWeekends(int amountOfDays) {

        int amountOfWeekends = 0;
        int partAmountOfWeekends;

        if (amountOfDays == 7) {
            amountOfWeekends = 2;
            System.out.println("The amount of weekends is " + amountOfWeekends);
        }
        if (amountOfDays%7 == 0) {
            amountOfWeekends = amountOfDays/7*2;
            System.out.println("The amount of weekends is: " + amountOfWeekends);
        }
        if (amountOfDays%7 != 0) {
            int restOfDays = amountOfDays%7;

            if((amountOfDays - restOfDays)%7 == 0) {
                partAmountOfWeekends = (amountOfDays - restOfDays)/7*2;
                int indexOfEndDay = calendarEndDay.get(Calendar.DAY_OF_WEEK);

                for (int i = indexOfEndDay; i > indexOfEndDay - restOfDays; i--) {

                    if (i == 7 || i == 1) {
                        partAmountOfWeekends++;
                    }

                    restOfDays--;
                    amountOfWeekends = partAmountOfWeekends;
                }
                System.out.println("The amount of weekends is: " + amountOfWeekends);
            }
        }
    }
}
