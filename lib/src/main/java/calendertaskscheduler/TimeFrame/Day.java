package calendertaskscheduler.TimeFrame;

public class Day extends TimeFrame {
    public Day(byte v) {
        super(v);
    }
    public Day() {
        super();
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
    public Byte getLower() {
        return 1;
    }
    public Byte getUpper() {
        return 31;
    }
}
