package TimeFrame;

public class Min extends TimeFrame {
    public Min(byte v) {
        super(v);
    }

    public byte getLower() {
        return 0;
    }
    public byte getUpper() {
        return 59;
    }
}
