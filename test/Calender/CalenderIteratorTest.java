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
            new FilteredTimeFrameIter<Min>(new Min((byte)0), empty_calender.mins.get());

        System.out.print("    ");
        for (int i = 0; i < 61; i++) {
            if (min_iter1.next()) System.out.print("!carry!>");
            System.out.print(min_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Min> min_iter2 =
            new FilteredTimeFrameIter<Min>(new Min((byte)0), general_calender.mins.get());

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
            new FilteredTimeFrameIter<Hour>(new Hour((byte)0), empty_calender.hours.get());

        System.out.print("    ");
        for (int i = 0; i < 25; i++) {
            if (hour_iter1.next()) System.out.print("!carry!>");
            System.out.print(hour_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Hour> hour_iter2 =
            new FilteredTimeFrameIter<Hour>(new Hour((byte)0), general_calender.hours.get());

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
            empty_calender.months.get(),
            empty_calender.weekdays.get(),
            empty_calender.days.get());

        System.out.print("    ");
        for (int i = 0; i < 32; i++) {
            if (date_iter1.nextDay()) System.out.print("!carry!>");
            System.out.print(date_iter1.show() + " ");
        }
        System.out.println();

        FilteredDateIterator date_iter2 = new FilteredDateIterator(
            LocalDate.now(),
            general_calender.months.get(),
            general_calender.weekdays.get(),
            general_calender.days.get());

        System.out.print("    ");
        for (int i = 0; i < 30; i++) {
            if (date_iter2.nextDay()) System.out.print("!carry!>");
            System.out.print(date_iter2.show() + " ");
        }
        System.out.println();

        System.out.println("DAY OF WEEK TEST: " + LocalDateTime.now());

        FilteredDateIterator date_iter3 = new FilteredDateIterator(
            LocalDate.now(),
            general_calender_w.months.get(),
            general_calender_w.weekdays.get(),
            general_calender_w.days.get());

        System.out.print("    ");
        for (int i = 0; i < 20; i++) {
            if (date_iter3.nextDay()) System.out.print("!carry!>");
            System.out.print(date_iter3.show() + " ");
        }
        System.out.println();

        System.out.println("DAY AND WEEK SPECIFIED: " + LocalDate.now());

        FilteredDateIterator date_iter4= new FilteredDateIterator(
            LocalDate.now(),
            general_calender_wd.months.get(),
            general_calender_wd.weekdays.get(),
            general_calender_wd.days.get());

        System.out.print("    ");
        for (int i = 0; i < 50; i++) {
            if (date_iter4.nextDay())  System.out.print("!carry!>");
            System.out.print(date_iter4.show() + " ");
        }
        System.out.println();
    }

    @Test
    public void filteredDateTimeIteratorTest() {
        System.out.println("FilteredDateTimeIteratorTest");
        System.out.println("General data.");
        Calender cal = new Calender("0,59 0,23 1,31 1,12 0,6");
        Calender daily = new Calender("0 0 * * *");
        Calender weekly = new Calender("0 0 * * 0");
        FilteredDateTimeIterator iter = new FilteredDateTimeIterator(cal);
        for (int i = 0; i < 150; i++) {
            System.out.println(iter.show());
            iter.next();
        }

        System.out.println("Empty data.");
        FilteredDateTimeIterator iter2 = new FilteredDateTimeIterator(empty_calender);
        for (int i = 0; i < 150; i++) {
            System.out.println(iter2.show());
            iter2.next();
        }

        System.out.println("Daily Task.");
        FilteredDateTimeIterator iter3 = new FilteredDateTimeIterator(daily);
        for (int i = 0; i < 150; i++) {
            System.out.println(iter3.show());
            iter3.next();
        }

        System.out.println("Weekly Task.");
        FilteredDateTimeIterator iter4 = new FilteredDateTimeIterator(weekly);
        for (int i = 0; i < 150; i++) {
            System.out.println(iter4.show());
            iter4.next();
        }
    }
}
