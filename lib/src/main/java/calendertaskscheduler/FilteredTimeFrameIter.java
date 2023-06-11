package calendertaskscheduler;

import calendertaskscheduler.TimeFrame.TimeFrame;
import java.util.Optional;
import java.util.TreeSet;

// An Iterator implementation for Min and Hour.
public class FilteredTimeFrameIter<T extends TimeFrame> implements CarryAwareIterator<TimeFrame> {
    Optional<TreeSet<T>> filter;
    T iter;

    public FilteredTimeFrameIter(T iter, Optional<TreeSet<T>> filter) {
        this.filter = filter;
        this.iter = iter;
    }

    public void setToMinimum() {
        this.iter.set(this.iter.getLower());
    }
    public boolean next() {
        if (filter.isEmpty()) {
            boolean carry = iter.get() == iter.getUpper();
            iter.set((byte) ((iter.get()+1) % (1 + iter.getUpper() - iter.getLower())));
            return carry;
        } else {
            for (T candidate: filter.get()) {
                if (candidate.compareTo(iter) > 0) {
                    iter.set(candidate.get());
                    return false;
                }
            }
            iter.set(filter.get().first().get());
            return true;
        }
    }

    public boolean isSpecificTimeFrame() {
        return filter.map(t -> t
            .contains(iter))
            .orElse(true);
    }

    public String show() {
        return String.valueOf(iter.get());
    }
}
