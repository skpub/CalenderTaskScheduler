package TimeFrame;

public abstract class TimeFrame implements Boundary {
    Byte frame;

    public abstract byte getLower();
    public abstract byte getUpper();
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
}
