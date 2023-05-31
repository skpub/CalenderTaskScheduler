package Calender.TimeFrame;

public class Hour extends TimeFrame {
    public Hour(byte v) {
        super(v);
    }
    public Hour() {
        super();
    }

    public Byte getLower() {
        return 0;
    }
    public Byte getUpper() {
        return 23;
    }
}
