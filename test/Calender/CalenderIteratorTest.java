package Calender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalenderIteratorTest {
    @Test
    public void test() {

        Calender calender = new Calender("* * * * *");
        CalenderIterator calender_iter = new CalenderIterator(calender);
        System.out.println(calender_iter.tf_date.toLocalDateTime());
    }
}