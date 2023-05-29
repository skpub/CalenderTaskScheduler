package Calender;

import Calender.TimeFrame.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CalenderIteratorTest {
    @Test
    public void test() {
        System.out.println(LocalDateTime.now());

        Calender calender = new Calender("* 4 1 * 2");
        FilteredTimeFrameIter<Min> min_iter= new FilteredTimeFrameIter<Min>((byte)0, calender.optional_mins);

        for (int i = 0; i < 100; i++) {
            System.out.println(min_iter.show());
            min_iter.next();
        }
    }
}