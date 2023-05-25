package TimeFrame;

public class Month extends TimeFrame {
    public Month(byte v) {
        super(v);
    }

    public byte getLower() {
        return 1;
    }
    public byte getUpper() {
        return 12;
    }
}
