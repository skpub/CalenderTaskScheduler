package Calender;

import Calender.TimeFrame.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class CalenderIteratorTest {
    private Calender empty_calender = new Calender("* * * * *");
    private Calender general_calender = new Calender("0,15,30,45 0,6,12,18 * * *");
    @Test
    public void minTest() {
        System.out.println("MIN TEST: " + LocalDateTime.now());

        FilteredTimeFrameIter<Min> min_iter1 =
            new FilteredTimeFrameIter<Min>(new Min((byte)0), empty_calender.optional_mins);

        for (int i = 0; i < 61; i++) {
            if (min_iter1.next()) System.out.print("!carry!>");
            System.out.print(min_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Min> min_iter2 =
            new FilteredTimeFrameIter<Min>(new Min((byte)0), general_calender.optional_mins);

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

        for (int i = 0; i < 25; i++) {
            if (hour_iter1.next()) System.out.print("!carry!>");
            System.out.print(hour_iter1.show() + " ");
        }
        System.out.println();

        FilteredTimeFrameIter<Hour> hour_iter2 =
            new FilteredTimeFrameIter<Hour>(new Hour((byte)0), general_calender.optional_hours);

        for (int i = 0; i < 25; i++) {
            if (hour_iter2.next()) System.out.print("!carry!>");
            System.out.print(hour_iter2.show() + " ");
        }
        System.out.println();
    }
}