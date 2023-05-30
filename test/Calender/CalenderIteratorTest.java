package Calender;

import Calender.TimeFrame.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class CalenderIteratorTest {
    private Calender empty_calender = new Calender("* * * * *");
    private Calender general_calender = new Calender("0,15,30,45 0,6,12,18 1,15,30 * *");
    private Calender general_calender_w = new Calender("0,15,30,45 0,6,12,18 * * 0,4,6");
    private Calender general_calender_wd = new Calender("* * 1,10,20,30 * 0,2,4");
    @Test
    public void minTest() {
        System.out.println("MIN TEST: " + LocalDateTime.now());

        FilteredTimeFrameIter<Min> min_iter1 =
            new FilteredTimeFrameIter<Min>(new Min((byte)0), empty_calender.optional_mins);

        System.out.print("    ");
        for (int i = 0; i < 61; i++) {
            if (min_iter1.next()) System.out.print("!carry!>");
            System.out.print(min_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Min> min_iter2 =
            new FilteredTimeFrameIter<Min>(new Min((byte)0), general_calender.optional_mins);

        System.out.print("    ");
        for (int i = 0; i < 25; i++) {
            if (min_iter2.next()) System.out.print("!carry!>");
            System.out.print(min_iter2.show() + " ");
        }
        System.out.println();
    }

    @Test
    public void hourTest() {
        System.out.println("HOUR TEST: " + LocalDateTime.now());

        FilteredTimeFrameIter<Hour> hour_iter1 =
            new FilteredTimeFrameIter<Hour>(new Hour((byte)0), empty_calender.optional_hours);

        System.out.print("    ");
        for (int i = 0; i < 25; i++) {
            if (hour_iter1.next()) System.out.print("!carry!>");
            System.out.print(hour_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Hour> hour_iter2 =
            new FilteredTimeFrameIter<Hour>(new Hour((byte)0), general_calender.optional_hours);

        System.out.print("    ");
        for (int i = 0; i < 25; i++) {
            if (hour_iter2.next()) System.out.print("!carry!>");
            System.out.print(hour_iter2.show() + " ");
        }
        System.out.println();
    }
    @Test
    public void DateTest() {
        System.out.println("DAY OF MONTH TEST: " + LocalDateTime.now());
        FilteredDateIterator date_iter1 = new FilteredDateIterator(
            LocalDate.now(),
            empty_calender.optional_months,
            empty_calender.optional_weekdays,
            empty_calender.optional_days);

        System.out.print("    ");
        for (int i = 0; i < 32; i++) {
            if (date_iter1.next()) System.out.print("!carry!>");
            System.out.print(date_iter1.show() + " ");
        }
        System.out.println();

        FilteredDateIterator date_iter2 = new FilteredDateIterator(
            LocalDate.now(),
            general_calender.optional_months,
            general_calender.optional_weekdays,
            general_calender.optional_days);

        System.out.print("    ");
        for (int i = 0; i < 30; i++) {
            if (date_iter2.next()) System.out.print("!carry!>");
            System.out.print(date_iter2.show() + " ");
        }
        System.out.println();

        System.out.println("DAY OF WEEK TEST: " + LocalDateTime.now());

        FilteredDateIterator date_iter3 = new FilteredDateIterator(
            LocalDate.now(),
            general_calender_w.optional_months,
            general_calender_w.optional_weekdays,
            general_calender_w.optional_days);

        System.out.print("    ");
        for (int i = 0; i < 20; i++) {
            if (date_iter3.next()) System.out.print("!carry!>");
            System.out.print(date_iter3.show() + " ");
        }
        System.out.println();

        System.out.println("DAY AND WEEK SPECIFIED: " + LocalDate.now());

        FilteredDateIterator date_iter4= new FilteredDateIterator(
            LocalDate.now(),
            general_calender_wd.optional_months,
            general_calender_wd.optional_weekdays,
            general_calender_wd.optional_days);

        System.out.print("    ");
        for (int i = 0; i < 50; i++) {
            if (date_iter4.next())  System.out.print("!carry!>");
            System.out.print(date_iter4.show() + " ");
        }
        System.out.println();
    }
}
