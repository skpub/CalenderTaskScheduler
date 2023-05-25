package TimeFrame;

public class Day extends TimeFrame {
    public Day(byte v) {
        super(v);
    }

    public byte getLower() {
        return 0;
    }
    public byte getUpper() {
        return 31;
    }
}
