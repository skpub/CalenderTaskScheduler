package TimeFrame;

public interface Boundary<T> {
    byte getLower();
    byte getUpper();
    boolean validate();
}
