package Calender.TimeFrame;

public class Min extends TimeFrame {
    public Min(byte v) {
        super(v);
    }
    public Min() {
        super();
    }

    public Byte getLower() {
        return 0;
    }
    public Byte getUpper() {
        return 59;
    }
}
