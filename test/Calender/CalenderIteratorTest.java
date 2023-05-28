package Calender;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CalenderIteratorTest {
    @Test
    public void test() {
        System.out.println(LocalDateTime.now());

        Calender calender = new Calender("30 4 1 * 2");
        CalenderIterator calender_iter = new CalenderIterator(calender);
        System.out.println(calender_iter.tf_date.toLocalDateTime());
        calender_iter.next();
        System.out.println(calender_iter.tf_date.toLocalDateTime());
    }
}