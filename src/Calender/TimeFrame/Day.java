package Calender.TimeFrame;

import java.util.Objects;

public class Day extends TimeFrame {
    public Day(byte v) {
        super(v);
    }
    public Day() {
        super();
    }

    public boolean isZero() {
        return this.frame == this.getLower();
    }
    public byte getLastDay(int year, Month month) {
        if (
                month.frame == 1 ||
                month.frame == 3 ||
                month.frame == 5 ||
                month.frame == 7 ||
                month.frame == 8 ||
                month.frame == 10 ||
                month.frame == 12)
        {
            return 31;
        }
        if (
            month.frame == 4 ||
            month.frame == 6 ||
            month.frame == 9 ||
            month.frame == 11)
        {
            return 30;
        } else {
            // month.frame == 2
            if (
                year % 400 == 0 || (
                year % 4 == 0 &&
                year % 100 != 0)
            )
                return 29;
            else
                return 28;
        }

    }
    public boolean isLastDay(int year, Month month) {
        return this.frame == getLastDay(year, month);
    }

    public static boolean next(int year, Month month, Day day, Day ret) {
        byte last_day = day.getLastDay(year, month);
        if (Objects.equals(day.frame, last_day)) {
            ret.frame = ret.getLower();
            return true;
        }
        ret.frame = (byte) (day.frame + 1);
        return false;
    }

    public static boolean plus_n(int year, Month month, Day day, Day ret, int n) {
        byte diff = (byte) (day.frame + n % (day.getLastDay(year, month) - 1));
        ret.frame = (byte) (1 + diff);
        return day.frame + n > day.getLastDay(year, month);
    }

    public Byte getLower() {
        return 0;
    }
    public Byte getUpper() {
        return 31;
    }

    public byte get(int year, Month month) {
        if (this.frame == 0)
            return getLastDay(year, month);
        else
            return this.frame;
    }
}
