package TimeFrame;

import java.util.Collection;
import java.util.List;

public interface CyclicNext<T extends Comparable<T> & Boundary<T>> {
//    public T next(T v);
    default public T next(Collection<T> collection, T v) {
        for (T w: collection) {
            if (w.compareTo(v) > 0) {
                return w;
            }
        }
        return v.getLower();
    }
}
