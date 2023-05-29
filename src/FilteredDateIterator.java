import Calender.CarryAwareIterator;
import Calender.TimeFrame.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;
import java.util.TreeSet;

public class FilteredDateIterator implements CarryAwareIterator<ChronoLocalDate> {
    Optional<TreeSet<Month>> filter_m;
    Optional<TreeSet<Weekday>> filter_w;
    Optional<TreeSet<Day>> filter_d;
    LocalDateTime iter;

    public FilteredDateIterator(
        LocalDateTime iter,
        Optional<TreeSet<Month>> filter_m,
        Optional<TreeSet<Weekday>> filter_w,
        Optional<TreeSet<Day>> filter_d
    ) {
        this.filter_m = filter_m;
        this.filter_w = filter_w;
        this.filter_d = filter_d;
        this.iter = iter;
    }

    @Override
    public boolean next() {
        if (this.filter_d.isEmpty() && this.filter_w.isEmpty()) {
            boolean carry = iter.getDayOfMonth() > iter.plusDays(1).getDayOfMonth();
            iter = iter.plusDays(1);
            return carry;
        }
        return false;
    }
}
