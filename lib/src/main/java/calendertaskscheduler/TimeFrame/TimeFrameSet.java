package calendertaskscheduler.TimeFrame;

import java.util.*;

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
            if (str.contains("/")) {
                int per = Integer.valueOf(str.split("/")[1]);
                T temp = TimeFrame.getInstance(clazz);
                for (int i = temp.getLower(); i < temp.getUpper(); i=i+per) {
                    temp = TimeFrame.getInstance(clazz);
                    temp.set((byte)i);
                    this.add(temp);
                }
            } else {
                Arrays.stream(str.split(","))
                    .forEach(serial -> {
                        List<String> values = Arrays.stream(serial.split("-")).toList();
                        if (values.size() == 2) {
                            for (int i = Integer.parseInt(values.get(0)); i <= Integer.parseInt(values.get(1)); i++) {
                                T temp = TimeFrame.getInstance(clazz);
                                temp.set((byte)i);
                                this.add(temp);
                            }
                        } else {
                            T temp = TimeFrame.getInstance(clazz);
                            temp.set(Byte.parseByte(values.get(0)));
                            this.add(temp);
                        }
                    });
            }
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
