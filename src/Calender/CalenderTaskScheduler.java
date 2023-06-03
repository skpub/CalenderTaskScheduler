package Calender;

import java.util.Timer;
import java.util.TimerTask;

public class CalenderTaskScheduler {
    private CalenderTask calender_task;
    private FilteredDateTimeIterator iter;
    private Timer timer;
    CalenderTaskScheduler(Calender calender, Runnable task) {
        this.timer = new Timer();
        this.iter = new FilteredDateTimeIterator(calender);
        this.timer.schedule(new CalenderTask (timer, task, this.iter), iter.getDate());
        calender_task = new CalenderTask(timer, task, new FilteredDateTimeIterator(calender));
    }
}

class CalenderTask extends TimerTask {
    Timer timer;
    Runnable task;
    FilteredDateTimeIterator iter;

    CalenderTask(Timer timer, Runnable task, FilteredDateTimeIterator iter) {
        this.timer = timer;
        this.iter = iter;
        this.task = task;
    }
    @Override
    public void run () {
        timer.schedule(new TimerTask() {
            public void run() {
                task.run();
            }
        }, iter.getDate());
        iter.next();
    }
}