package Calender;

import Calender.TimeFrame.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

public class CalenderIterator {
    Calender calender;
    TimeFrameDate tf_date;

    CalenderIterator(Calender calender) {
        this.calender = calender;

        LocalDateTime now = LocalDateTime.now();
        Month month =       new Month((byte) now.getMonthValue());
        Day day =           new Day((byte) now.getDayOfMonth());
        Hour hour =         new Hour((byte) now.getHour());
        Min min =           new Min((byte) now.getMinute());
        Weekday weekday =   new Weekday((byte) (now.getDayOfWeek().getValue() - 1));
        int year =          now.getYear();
        tf_date = new TimeFrameDate(month, day, weekday, hour, min, year);

        // Is that Date valid?
        if (calender.optional_weekdays.isEmpty() || calender.optional_days.isEmpty()) {
            tf_date.day.set((byte) now.getDayOfMonth());
            tf_date.weekday.set((byte) (now.getDayOfWeek().getValue() - 1));
            tf_date.month.set((byte) now.getMonthValue());
        }
        this.next();
    }

    public void next() {
        Min min =           new Min(tf_date.min.get());
        Hour hour =         new Hour(tf_date.hour.get());
        Day day =           new Day(tf_date.day.get());
        Month month =       new Month(tf_date.month.get());
        Weekday weekday =   new Weekday(tf_date.weekday.get());
        int year =          tf_date.year;

        // Min
        boolean carry = rippleCarryNext(calender.optional_mins, tf_date.min, min);
        // Hour
        if (carry) {
            carry = rippleCarryNext(calender.optional_hours, tf_date.hour, hour);
            // Day || Weekday
            if (carry) {
                if (calender.optional_days.isEmpty() || calender.optional_weekdays.isEmpty())  {
                    // Empty means that it is expected to be executed every day.
                    carry = Day.next(year, tf_date.month, tf_date.day, day);
                } else {
                    boolean found = false;
                    int diff_day = 0;
                    int diff_weekday = 0;

                    for (Day candidate: calender.optional_days.get()) {
                        if ((diff_day = candidate.compareTo(tf_date.day)) > 0) {
                            day = candidate;
                            carry = false;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        diff_day = day.getLastDay(year, tf_date.month)
                            + calender.optional_days.get().first().get() - tf_date.day.get();
//                        day = calender.optional_days.get().first();
                        carry = true;
                    }
                    // ------------- Weekday
                    found = false;
                    for (Weekday candidate: calender.optional_weekdays.get()) {
                        if ((diff_weekday = candidate.compareTo(tf_date.weekday)) > 0) {
                            weekday = candidate;
                            carry = false;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        diff_weekday = weekday.getUpper()
                            + calender.optional_days.get().first().get() - tf_date.weekday.get();
                    }

                    if (diff_day < diff_weekday) {
                        carry = Day.plus_n(year, month, tf_date.day, day, diff_day);
                        Weekday.plus_n(tf_date.weekday, weekday, diff_day);
                    } else {
                        carry = Day.plus_n(year, month, tf_date.day, day, diff_weekday);
                        Weekday.plus_n(tf_date.weekday, weekday, diff_weekday);
                    }
                }
                // Month
                if (carry) {
                    carry = rippleCarryNext(calender.optional_months, tf_date.month, month);
                    if (carry) year++;
                } else {
                    month.set(tf_date.month.get());
                }
            } else {
                day.set(tf_date.day.get());
                weekday.set(tf_date.weekday.get());
            }
        } else {
            hour.set(tf_date.hour.get());
        }
        tf_date = new TimeFrameDate(month, day, weekday, hour, min, year);
    }

//    private static boolean rippleCarryInitDay(
//        Optional<TreeSet<Day>> days_collection,
//        Optional<TreeSet<Weekday>> weekdays_collection,
//        LocalDate from,
//        LocalDate to
//        )
//    {
//        if (days_collection.isEmpty() || weekdays_collection.isEmpty()) {
//            to = from;
//            return false;
//        }
//    }

    private static <T extends TimeFrame> boolean rippleCarryNext(Optional<TreeSet<T>> collection, T tf, T ret) {
        if (collection.isEmpty()) {
            // Empty means that it is expected to be executed every period.
            return T.next(tf, ret);
        }
        for (T candidate: collection.get()) {
            if (candidate.compareTo(tf) > 0) {
                ret.set(candidate.get());
                return false;
            }
        }
        ret.set(collection.get().first().get()); // Lowest value.
        return true;
    }
}
