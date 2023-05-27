package Calender;

import Calender.TimeFrame.*;

import java.util.*;

public class Calender {
    Optional<TreeSet<Min>>      optional_mins;
    Optional<TreeSet<Hour>>     optional_hours;
    Optional<TreeSet<Day>>      optional_days;
    Optional<TreeSet<Month>>    optional_months;
    Optional<TreeSet<Weekday>>  optional_weekdays;
    TimeFrameDate next_date;

    public void set_next_date() {
    }

    public Calender (String str) throws IllegalArgumentException {
        List<String> settings = new ArrayList<>(Arrays.asList(str.split(" ")));
        if (settings.size() != 5) {
            throw new IllegalArgumentException("Expected 5 elements when divided by space, but "
                + settings.size() + " elements were passed.");
        }

        if (Objects.equals(settings.get(0), "*")) {
            optional_mins = Optional.empty();
        } else {
            optional_mins = Optional.of(new TreeSet<Min>());
            Arrays.stream(settings.get(0).split(","))
                .forEach(v -> {
                    try {
                        Min temp = new Min(Byte.parseByte(v));
                        optional_mins.get().add(temp);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
        }

        if (Objects.equals(settings.get(1), "*")) {
            optional_hours = Optional.empty();
        } else {
            optional_hours = Optional.of(new TreeSet<Hour>());
            Arrays.stream(settings.get(1).split(","))
                .forEach(v -> {
                    try {
                        Hour temp = new Hour(Byte.parseByte(v));
                        optional_hours.get().add(temp);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
        }

        if (Objects.equals(settings.get(2), "*")) {
            optional_days = Optional.empty();
        } else {
            optional_days = Optional.of(new TreeSet<Day>());
            Arrays.stream(settings.get(2).split(","))
                .forEach(v -> {
                    try {
                        Day temp = new Day(Byte.parseByte(v));
                        optional_days.get().add(temp);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
        }

        if (Objects.equals(settings.get(3), "*")) {
            optional_months = Optional.empty();
        } else {
            optional_months = Optional.of(new TreeSet<Month>());
            Arrays.stream(settings.get(3).split(","))
                .forEach(v -> {
                    try {
                        Month temp = new Month(Byte.parseByte(v));
                        optional_months.get().add(temp);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
        }

        if (Objects.equals(settings.get(4), "*")) {
            optional_weekdays = Optional.empty();
        } else {
            optional_weekdays = Optional.of(new TreeSet<Weekday>());
            Arrays.stream(settings.get(4).split(","))
                .forEach(v -> {
                    try {
                        Weekday temp = new Weekday(Byte.parseByte(v));
                        optional_weekdays.get().add(temp);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
        }
    }

    public String show() {
        StringBuilder temp = new StringBuilder();
        optional_mins.ifPresentOrElse(
            slots -> {
                for (Min slot: slots.headSet(slots.last())) temp.append(slot).append(",");
                temp.append(slots.last());
            },
            () -> {
                temp.append("*");
            }
        );
        temp.append(" ");

        optional_hours.ifPresentOrElse(
            slots -> {
                for (Hour slot: slots.headSet(slots.last())) temp.append(slot).append(",");
                temp.append(slots.last());
            },
            () -> {
                temp.append("*");
            }
        );
        temp.append(" ");

        optional_days.ifPresentOrElse(
            slots -> {
                for (Day slot: slots.headSet(slots.last())) temp.append(slot).append(",");
                temp.append(slots.last());
            },
            () -> {
                temp.append("*");
            }
        );
        temp.append(" ");

        optional_months.ifPresentOrElse(
            slots -> {
                for (Month slot: slots.headSet(slots.last())) temp.append(slot).append(",");
                temp.append(slots.last());
            },
            () -> {
                temp.append("*");
            }
        );
        temp.append(" ");

        optional_weekdays.ifPresentOrElse(
            slots -> {
                for (Weekday slot: slots.headSet(slots.last())) temp.append(slot).append(",");
                temp.append(slots.last());
            },
            () -> {
                temp.append("*");
            }
        );

        return temp.toString();
    }
}
