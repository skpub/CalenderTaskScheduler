package Calender;

import Calender.TimeFrame.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

public class CalenderIterator implements Iterator<TimeFrameDate> {
    Calender calender;
    TimeFrameDate tf_date;
    int year;

    CalenderIterator(Calender calender, TimeFrameDate tf_date) {
        this.calender = calender;
        this.tf_date = tf_date;
        this.year = LocalDate.now().getYear();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public TimeFrameDate next() {
        Min min =           new Min();
        Hour hour =         new Hour();
        Day day =           new Day();
        Month month =       new Month();
        Weekday weekday =   new Weekday();

        boolean ripple = rippleCarryNext(calender.optional_mins, tf_date.min, min);
        if (ripple) {
            ripple = rippleCarryNext(calender.optional_hours, tf_date.hour, hour);
        }
    }
//    public TimeFrameDate next() {
//        Min min = new Min(tf_date.min.getLower());
//        Hour hour = new Hour(tf_date.hour.getLower());
//        Day day = new Day(tf_date.day.getLower());
//        Month month = new Month(tf_date.month.getLower());
//        Weekday weekday = new Weekday(tf_date.weekday.getLower());
//
//        boolean ripple = rippleCarryNext(calender.optional_mins, tf_date.min, min);
//        if (ripple) {
//            ripple = rippleCarryNext(calender.optional_hours, tf_date.hour, hour);
//            if (ripple) {
//                if (calender.optional_days.isEmpty()) {
//
//                }
//            }
//        }
//
//    }

    public <T extends TimeFrame>boolean rippleCarryNext(Optional<TreeSet<T>> collection, T tf, T ret) {
        if (collection.isEmpty()) {
            return T.next(tf, ret);
        }
        for (T candidate: collection.get()) {
            if (candidate.compareTo(tf) > 0) {
                ret = candidate;
                return false;
            }
        }
        ret = collection.get().first(); // Lowest value.
        return true;
    }
}
