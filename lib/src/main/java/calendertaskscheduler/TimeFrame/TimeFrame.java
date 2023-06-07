package calendertaskscheduler.TimeFrame;

public abstract class TimeFrame implements Boundary<Byte>, Comparable<TimeFrame> {
    Byte frame;

    public abstract Byte getLower();
    public abstract Byte getUpper();
    public boolean validate() {
        return this.getLower() <= this.frame && this.frame <= this.getUpper();
    }

    public TimeFrame(byte v) {
        this.frame = v;
        if (!validate()) {
            throw new IllegalArgumentException(
                "expected " + getLower() + "-" + getUpper() + ", but you passed " + v
            );
        }
    }

    public byte get() {
        return frame;
    }

    public void set(byte v) {
        this.frame = v;
    }

    public TimeFrame() {
        this.frame = this.getLower();
    }

    public int compareTo(TimeFrame value) {
        return this.frame.compareTo(value.frame);
    }
}
