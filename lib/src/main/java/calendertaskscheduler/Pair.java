package calendertaskscheduler;

public class Pair<T, U> {
    T t;
    U u;
    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T first() {
        return t;
    }

    public U second() {
        return u;
    }
}
