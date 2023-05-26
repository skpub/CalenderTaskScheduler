package TimeFrame;

public abstract class TimeFrame implements Boundary<Byte>, Comparable<Byte> {
    Byte frame;

    public abstract Byte getLower();
    public abstract Byte getUpper();
    public boolean validate() {
        return getLower() <= frame && frame >= getUpper();
    }

    public TimeFrame(byte v) {
        this.frame = v;
        if (!validate()) {
            throw new IllegalArgumentException(
                "expected " + getLower() + "-" + getUpper() + ", but you passed " + v
            );
        }
    }

    public int compareTo(Byte value) {
        return frame.compareTo(value);
    }
}
