package TimeFrame;

public class Day extends TimeFrame {
    public Day(byte v) {
        super(v);
    }

    public Byte getLower() {
        return 0;
    }
    public Byte getUpper() {
        return 31;
    }
}
