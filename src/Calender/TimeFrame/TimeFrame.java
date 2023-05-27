package Calender.TimeFrame;

import java.util.Objects;

public abstract class TimeFrame implements Boundary<Byte>, Comparable<TimeFrame> {
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

    public int compareTo(TimeFrame value) {
        return this.frame.compareTo(value.frame);
    }

    public static boolean next(TimeFrame tf, TimeFrame ret) {
        if (Objects.equals(tf.frame, tf.getUpper())) {
            ret.frame = ret.getLower();
            return true;
        }
        ret.frame = (byte) (tf.frame + 1);
        return false;
    }
}
