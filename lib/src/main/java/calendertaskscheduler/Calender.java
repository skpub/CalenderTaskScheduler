package calendertaskscheduler;

import calendertaskscheduler.TimeFrame.*;
import java.util.*;

public class Calender {
    TimeFrameSet<Min> mins;
    TimeFrameSet<Hour> hours;
    TimeFrameSet<Day> days;
    TimeFrameSet<Month> months;
    TimeFrameSet<Weekday> weekdays;

    public Calender (String str) throws IllegalArgumentException {
        List<String> settings = new ArrayList<>(Arrays.asList(str.split(" ")));
        if (settings.size() != 5) {
            throw new IllegalArgumentException("Expected 5 elements when divided by space, but "
                + settings.size() + " elements were passed.");
        }

        this.mins = new TimeFrameSet<Min>(settings.get(0), Min.class);
        this.hours = new TimeFrameSet<Hour>(settings.get(1), Hour.class);
        this.days = new TimeFrameSet<Day>(settings.get(2), Day.class);
        this.months = new TimeFrameSet<Month>(settings.get(3), Month.class);
        this.weekdays = new TimeFrameSet<Weekday>(settings.get(4), Weekday.class);
//        if (Objects.equals(settings.get(0), "*")) {
//            optional_mins = Optional.empty();
//        } else {
//            optional_mins = Optional.of(new TreeSet<Min>());
//            Arrays.stream(settings.get(0).split(","))
//                .forEach(v -> {
//                    try {
//                        Min temp = new Min(Byte.parseByte(v));
//                        optional_mins.get().add(temp);
//                    } catch (IllegalArgumentException e) {
//                        throw new IllegalArgumentException(e);
//                    }
//                });
//        }
//
//        if (Objects.equals(settings.get(1), "*")) {
//            optional_hours = Optional.empty();
//        } else {
//            optional_hours = Optional.of(new TreeSet<Hour>());
//            Arrays.stream(settings.get(1).split(","))
//                .forEach(v -> {
//                    try {
//                        Hour temp = new Hour(Byte.parseByte(v));
//                        optional_hours.get().add(temp);
//                    } catch (IllegalArgumentException e) {
//                        throw new IllegalArgumentException(e);
//                    }
//                });
//        }
//
//        if (Objects.equals(settings.get(2), "*")) {
//            optional_days = Optional.empty();
//        } else {
//            optional_days = Optional.of(new TreeSet<Day>());
//            Arrays.stream(settings.get(2).split(","))
//                .forEach(v -> {
//                    try {
//                        Day temp = new Day(Byte.parseByte(v));
//                        optional_days.get().add(temp);
//                    } catch (IllegalArgumentException e) {
//                        throw new IllegalArgumentException(e);
//                    }
//                });
//        }
//
//        if (Objects.equals(settings.get(3), "*")) {
//            optional_months = Optional.empty();
//        } else {
//            optional_months = Optional.of(new TreeSet<Month>());
//            Arrays.stream(settings.get(3).split(","))
//                .forEach(v -> {
//                    try {
//                        Month temp = new Month(Byte.parseByte(v));
//                        optional_months.get().add(temp);
//                    } catch (IllegalArgumentException e) {
//                        throw new IllegalArgumentException(e);
//                    }
//                });
//        }
//
//        if (Objects.equals(settings.get(4), "*")) {
//            optional_weekdays = Optional.empty();
//        } else {
//            optional_weekdays = Optional.of(new TreeSet<Weekday>());
//            Arrays.stream(settings.get(4).split(","))
//                .forEach(v -> {
//                    try {
//                        Weekday temp = new Weekday(Byte.parseByte(v));
//                        optional_weekdays.get().add(temp);
//                    } catch (IllegalArgumentException e) {
//                        throw new IllegalArgumentException(e);
//                    }
//                });
//        }
    }

    public String show() {
        StringBuilder temp = new StringBuilder();
        temp.append(mins.show());
        temp.append(" ");
        temp.append(hours.show());
        temp.append(" ");
        temp.append(days.show());
        temp.append(" ");
        temp.append(months.show());
        temp.append(" ");
        temp.append(weekdays.show());
        return temp.toString();
    }
}
