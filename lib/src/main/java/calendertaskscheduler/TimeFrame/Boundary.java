package calendertaskscheduler.TimeFrame;

public interface Boundary<T> {
    T getLower();
    T getUpper();
    boolean validate();
}
