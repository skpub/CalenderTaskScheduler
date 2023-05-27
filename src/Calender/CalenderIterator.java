package Calender;

import Calender.TimeFrame.*;

import java.security.KeyPair;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class CalenderIterator {
    Calender calender;
    TimeFrameDate tf_date;

    CalenderIterator(Calender calender, TimeFrameDate tf_date) {
        this.calender = calender;
        this.tf_date = tf_date;
    }

    public TimeFrameDate next() {
        Min min = new Min(tf_date.min.getLower());
        boolean ripple = rippleCarryNext(calender.optional_mins, tf_date.min, min);
    }

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
