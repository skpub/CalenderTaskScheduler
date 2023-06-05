package Calender.TimeFrame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFrameSetTest {
    @Test
    void test() {
        var tfs_empty = new TimeFrameSet<Min>("*", Min.class);
        System.out.println(tfs_empty.show());

        var tfs_general = new TimeFrameSet<Min>("2-6", Min.class);
        System.out.println(tfs_general.show());
    }
}