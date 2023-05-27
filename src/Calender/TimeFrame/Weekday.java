package Calender.TimeFrame;

public class Weekday extends TimeFrame {
    public Weekday(byte v) {
        super(v);
    }
    public Weekday() {
        super();
    }

    public Byte getLower() {
        return 0;
    }
    public Byte getUpper() {
        return 6;
    }
}
