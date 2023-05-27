package Calender.TimeFrame;

public class Month extends TimeFrame {
    public Month(byte v) {
        super(v);
    }

    public Byte getLower() {
        return 1;
    }
    public Byte getUpper() {
        return 12;
    }
}
