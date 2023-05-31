package Calender;

import Calender.CarryAwareIterator;
import Calender.TimeFrame.*;
import Calender.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;
import java.util.TreeSet;

public class FilteredDateIterator {
    Optional<TreeSet<Month>> filter_m;
    Optional<TreeSet<Weekday>> filter_w;
    Optional<TreeSet<Day>> filter_d;
    LocalDate iter;

    public FilteredDateIterator(
        LocalDate iter,
        Optional<TreeSet<Month>> filter_m,
        Optional<TreeSet<Weekday>> filter_w,
        Optional<TreeSet<Day>> filter_d
    ) {
        this.filter_m = filter_m;
        this.filter_w = filter_w;
        this.filter_d = filter_d;
        this.iter = iter;
    }

    private byte getMax() {
        if (iter.getMonthValue() == 1 ||
            iter.getMonthValue() == 3 ||
            iter.getMonthValue() == 5 ||
            iter.getMonthValue() == 7 ||
            iter.getMonthValue() == 8 ||
            iter.getMonthValue() == 10 ||
            iter.getMonthValue() == 12
        )
            return 31;

        else if (
            iter.getMonthValue() == 4 ||
            iter.getMonthValue() == 6 ||
            iter.getMonthValue() == 9 ||
            iter.getMonthValue() == 11
        )
            return 30;

        else { // iter.getMonthValue() == 2
            if (iter.getYear() % 4 == 0 &&
                iter.getYear() % 100 != 0 ||
                iter.getYear() % 400 != 0)
                return 29;
            else
                return 28;
        }
    }

    private Pair<Boolean, LocalDate> day_next() {
        LocalDate ret;
        for (Day candidate: filter_d.get()) {
            if (
                candidate.get() > iter.getDayOfMonth() &&
                    candidate.get() <= getMax()
            ) {
                ret = iter.plusDays(candidate.get() - iter.getDayOfMonth());
                return new Pair<>(false, ret);
            }
        }
        ret = iter.plusDays(filter_d.get().first().get() + getMax() - iter.getDayOfMonth());
        return new Pair<>(true, ret);
    }

    private Pair<Boolean, LocalDate> week_next() {
        LocalDate ret;
        for (Weekday candidate: filter_w.get()) {
            if (candidate.get() > transJ2C(iter.getDayOfWeek().getValue())) {
                ret = iter.plusDays(candidate.get() - transJ2C(iter.getDayOfWeek().getValue()));
                return new Pair<>(false, ret);
            }
        }
        byte week_max = (byte) (filter_w.get().first().getUpper() + 1);
        ret = iter.plusDays(filter_w.get().first().get() + week_max - transJ2C(iter.getDayOfWeek().getValue()));
        return new Pair<>(true, ret);
    }

    public boolean nextDay() {
        if (this.filter_d.isEmpty() && this.filter_w.isEmpty()) {
            boolean carry = iter.getDayOfMonth() > iter.plusDays(1).getDayOfMonth();
            iter = iter.plusDays(1);
            return carry;
        }

        if (this.filter_d.isEmpty()) { // filter_d: empty, filter_w: present
            Pair<Boolean, LocalDate> result = week_next();
            iter = result.second();
            return result.first();
        }

        if (this.filter_w.isEmpty()) { // filter_d: present, filter_w: empty
            Pair<Boolean, LocalDate> result = day_next();
            iter = result.second();
            return result.first();
        }

        // filter_d: present, filter_w: present.
        Pair<Boolean, LocalDate> day_candidate = day_next();
        Pair<Boolean, LocalDate> week_candidate = week_next();

        if (day_candidate.second().isAfter(week_candidate.second())) {
            iter = week_candidate.second();
            return week_candidate.first();
        } else {
            iter = day_candidate.second();
            return day_candidate.first();
        }
    }

    public boolean isSpecificMonth() {
        return filter_m.map(months -> months
            .contains(new Month((byte)iter.getMonthValue())))
            .orElse(true);
    }

    public boolean isSpecificDay() {
        boolean is_specific_day = filter_d.map(day -> day
            .contains(new Day((byte)iter.getDayOfMonth())))
            .orElse(true);

        boolean is_specific_week = filter_w.map(week -> week
            .contains(new Weekday((byte)transJ2C(iter.getDayOfWeek().getValue()))))
            .orElse(true);

        return is_specific_day && is_specific_week;
    }

    public void nextMonth() {
        if (filter_m.isEmpty()) {
            iter = iter.plusMonths(1);
        } else {
            for (Month candidate: filter_m.get()) {
                if (candidate.get() > iter.getMonthValue()) {
                    iter = LocalDate.of(iter.getYear(), candidate.get(), 1);
                    return;
                }
            }
            iter = LocalDate.of(iter.getYear() + 1, filter_m.get().first().get(), 1);
        }
    }

    private int transJ2C(int v) { // Java expression to Crontab expression. (1-7 to 0-6).
        return v == 0 ? 0 : v-1;
    }

    public String show() {
        return String.valueOf(iter);
    }
}
