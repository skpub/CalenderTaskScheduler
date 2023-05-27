package Calender.TimeFrame;

import java.util.SortedSet;

public interface CyclicNext<T extends Comparable<T>> {
//    public T next(T v);
    default public T next(SortedSet<T> collection, T v) {
        for (T w: collection) {
            if (w.compareTo(v) > 0) {
                return w;
            }
        }
        return collection.first(); // Lowest element.
    }
}
