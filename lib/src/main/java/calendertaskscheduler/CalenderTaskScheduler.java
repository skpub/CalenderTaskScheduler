package calendertaskscheduler;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalenderTaskScheduler implements Runnable {
    FilteredDateTimeIterator iter;
    Runnable task;

    public CalenderTaskScheduler(Calender calender, Runnable task) {
        this.iter = new FilteredDateTimeIterator(calender);
        this.task = task;
    }
    public void run() {
        while(true) {
            LocalDateTime next_exec_time = iter.getTime();
            LocalDateTime now = LocalDateTime.now();
            Duration next_sub_now;
            while ((next_sub_now = Duration.between(now, next_exec_time)).toMillis() < 0) ;
            try {
                Thread.sleep(next_sub_now.toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            task.run();
            iter.next();
        }
    }
}