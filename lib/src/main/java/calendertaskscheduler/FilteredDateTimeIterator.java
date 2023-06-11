package calendertaskscheduler;

import calendertaskscheduler.TimeFrame.Hour;
import calendertaskscheduler.TimeFrame.Min;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FilteredDateTimeIterator {
    private FilteredTimeFrameIter<Min> min_iter;
    private FilteredTimeFrameIter<Hour> hour_iter;
    private FilteredDateIterator date_iter;
    private Calender calender;

    public FilteredDateTimeIterator (Calender calender) {
        this.calender = calender;
        LocalDateTime now = LocalDateTime.now();
        min_iter = new FilteredTimeFrameIter<Min>(
            new Min((byte)now.getMinute()), calender.mins.get());
        hour_iter = new FilteredTimeFrameIter<Hour>(
            new Hour((byte)now.getHour()), calender.hours.get());
        date_iter = new FilteredDateIterator(
            now.toLocalDate(),
            calender.months.get(),
            calender.weekdays.get(),
            calender.days.get());

        boolean carry = false;
        if (carry = carry | !date_iter.isSpecificMonth())
            date_iter.nextMonth();
        if (carry = carry | !date_iter.isSpecificDay()) {
            date_iter.nextDay();
            hour_iter.setToMinimum();
        }
        if (carry = carry | !hour_iter.isSpecificTimeFrame()) {
            hour_iter.next();
            min_iter.setToMinimum();
        }
        if (carry = carry | !min_iter.isSpecificTimeFrame())
            min_iter.next();

        if (!carry)
            this.next();
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

    public LocalDateTime getTime() {
        return LocalDateTime.of(
            date_iter.iter.getYear(),
            date_iter.iter.getMonthValue(),
            date_iter.iter.getDayOfMonth(),
            hour_iter.iter.get(),
            min_iter.iter.get());
    }

    public Date getDate() {
        return Date.from(getTime().atZone(ZoneId.systemDefault()).toInstant());
    }
}
