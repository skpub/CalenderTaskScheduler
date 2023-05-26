import TimeFrame.*;

public class TimeFrameDate implements CyclicNext {
    Month month;
    Day day;
    Weekday weekday;
    Hour hour;
    Min min;

    TimeFrameDate(
        Month month,
        Day day,
        Weekday weekday,
        Hour hour,
        Min min)
    {
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.hour = hour;
        this.min = min;
    }
}
