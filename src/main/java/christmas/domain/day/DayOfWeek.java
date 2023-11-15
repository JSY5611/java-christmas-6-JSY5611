package christmas.domain.day;

import christmas.error.ErrorMessage;

public enum DayOfWeek {
    SUN("일요일", 3),
    MON("월요일", 4),
    TUE("화요일", 5),
    WED("수요일", 6),
    THU("목요일", 0),
    FRI("금요일", 1),
    SAT("토요일", 2);

    private int day;
    private String dayOfWeek;

    DayOfWeek(String dayOfweek, int day) {
        this.day = day;
        this.dayOfWeek = dayOfweek;
    }

    public int getDay() {
        return day;
    }

    public static DayOfWeek of(int dayOfWeek) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.day == dayOfWeek) {
                return day;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.DAY_ERROR.getMessage());
    }

}
