package Calender;

import Calender.TimeFrame.*;

import java.time.LocalDateTime;

public class TimeFrameDate {
    Month month;
    Day day;
    Weekday weekday;
    Hour hour;
    Min min;
    int year;

    TimeFrameDate(
        Month month,
        Day day,
        Weekday weekday,
        Hour hour,
        Min min,
        int year)
    {
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.hour = hour;
        this.min = min;
        this.year = year;
    }

    LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(
            year,
            month.get(),
            day.get(year, month),
            hour.get(),
            min.get());
    }
}
