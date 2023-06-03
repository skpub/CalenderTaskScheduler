package Calender;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

class CalenderTaskSchedulerTest {
    private Calender empty_calender = new Calender("* * * * *");
//    @Test
//    public void CalenderTaskSchedulerTest1() {
//        System.out.println("CalenderTaskSchedulerTest");
//        Runnable runnable_test = new RunnableTest();
//        CalenderTaskScheduler scheduler = new CalenderTaskScheduler(empty_calender, runnable_test);
//    }
//    class RunnableTest implements Runnable {
//        public void run() {
//            System.out.println("test");
//        }
//    }

    @Test
    public void test() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("test");
            }
        };
        LocalDateTime plus2 = LocalDateTime.now().plusSeconds(2);
        timer.schedule(task, Date.from(plus2.atZone(ZoneId.systemDefault()).toInstant()));
        timer.schedule(task, Date.from(plus2.plusSeconds(1).atZone(ZoneId.systemDefault()).toInstant()));
        timer.schedule(task, Date.from(plus2.plusSeconds(2).atZone(ZoneId.systemDefault()).toInstant()));
    }

}