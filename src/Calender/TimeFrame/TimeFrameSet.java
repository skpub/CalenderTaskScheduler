package Calender.TimeFrame;

import java.util.*;
import java.util.stream.Stream;

public class TimeFrameSet<T extends TimeFrame> {
    private Optional<TreeSet<T>> field;

    public Optional<TreeSet<T>> get() {
        return field;
    }

    public TimeFrameSet(String str, Class<T> clazz) {
        if (Objects.equals(str, "*"))
            this.field = Optional.empty();
        else {
            field = Optional.of(new TreeSet<T>());
            Arrays.stream(str.split(","))
                .forEach(serial -> {
                    List<String> values = Arrays.stream(serial.split("-")).toList();
                    if (values.size() == 2) {
                        for (int i = Integer.parseInt(values.get(0)); i <= Integer.parseInt(values.get(1)); i++) {
                            T temp;
                            try {
                                temp = clazz.newInstance();
                            } catch (ReflectiveOperationException e) {
                                throw new RuntimeException(e);
                            }
                            temp.set((byte)i);
                            this.add(temp);
                        }
                    } else {
                        T temp;
                        try {
                            temp = clazz.newInstance();
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                        temp.set(Byte.parseByte(values.get(0)));
                        this.add(temp);
                    }
                });
        }
    }

    void add(T elem) {
        if (field.isEmpty())
            field = Optional.of(new TreeSet<T>());
        else {
            field.get().add(elem);
        }
    }

    public String show() {
        StringBuilder temp = new StringBuilder();
        field.ifPresentOrElse(
            slots -> {
                for (T slot: slots.headSet(slots.last())) temp.append(slot.get()).append(",");
                temp.append(slots.last().get());
            },
            () -> {
                temp.append("*");
            }
        );
        return temp.toString();
    }
}
