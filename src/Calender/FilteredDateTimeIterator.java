package Calender;

import Calender.TimeFrame.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FilteredDateTimeIterator {
    private FilteredTimeFrameIter<Min> min_iter;
    private FilteredTimeFrameIter<Hour> hour_iter;
    private FilteredDateIterator date_iter;
    private Calender calender;

    FilteredDateTimeIterator(Calender calender) {
        this.calender = calender;
        LocalDateTime now = LocalDateTime.now();
        min_iter = new FilteredTimeFrameIter<Min>(
            new Min((byte)now.getMinute()), calender.optional_mins);
        hour_iter = new FilteredTimeFrameIter<Hour>(
            new Hour((byte)now.getHour()), calender.optional_hours);
        date_iter = new FilteredDateIterator(
            now.toLocalDate(),
            calender.optional_months,
            calender.optional_weekdays,
            calender.optional_days);

        if (!date_iter.isSpecificMonth())
            date_iter.nextMonth();
        if (!date_iter.isSpecificDay()) {
            date_iter.nextDay();
            hour_iter.setToMinimum();
        }
        if (!hour_iter.isSpecificTimeFrame()) {
            hour_iter.next();
            min_iter.setToMinimum();
        }
        if (!min_iter.isSpecificTimeFrame())
            min_iter.next();
    }

    public void next() {
        boolean carry = min_iter.next();
        if (carry)
            carry = hour_iter.next();
        if (carry) {
            date_iter.nextDay();
            if (!date_iter.isSpecificMonth()) {
                date_iter.nextMonth();
                if (!date_iter.isSpecificDay()) {
                    date_iter.nextDay();
                    hour_iter.setToMinimum();
                }
                if (!hour_iter.isSpecificTimeFrame()) {
                    hour_iter.next();
                    min_iter.setToMinimum();
                } if (!min_iter.isSpecificTimeFrame())
                    min_iter.next();
            }
        }
    }

    public String show() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd (E) HH:mm"
        );
        return formatter.format(LocalDateTime.of(
            date_iter.iter.getYear(),
            date_iter.iter.getMonthValue(),
            date_iter.iter.getDayOfMonth(),
            hour_iter.iter.get(),
            min_iter.iter.get()));
    }
}
