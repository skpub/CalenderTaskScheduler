package TimeFrame;

public class Weekday extends TimeFrame {
    public Weekday(byte v) {
        super(v);
    }

    public byte getLower() {
        return 0;
    }
    public byte getUpper() {
        return 6;
    }
}
