package TimeFrame;

public class Hour extends TimeFrame {
    public Hour(byte v) {
        super(v);
    }

    public byte getLower() {
        return 0;
    }
    public byte getUpper() {
        return 23;
    }
}
