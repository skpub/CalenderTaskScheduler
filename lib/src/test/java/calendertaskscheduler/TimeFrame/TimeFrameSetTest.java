package calendertaskscheduler.TimeFrame;

import org.junit.jupiter.api.Test;

import calendertaskscheduler.TimeFrame.Min;
import calendertaskscheduler.TimeFrame.TimeFrameSet;

class TimeFrameSetTest {
    @Test
    void test() {
        var tfs_empty = new TimeFrameSet<Min>("*", Min.class);
        System.out.println(tfs_empty.show());

        var tfs_general = new TimeFrameSet<Min>("2-6", Min.class);
        System.out.println(tfs_general.show());

        var tfs_per3 = new TimeFrameSet<Min>("/3", Min.class);
        System.out.println(tfs_per3.show());
    }
}