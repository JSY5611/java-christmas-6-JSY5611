package christmas.domain;

public enum DayOfWeek {
    SUN("일요일", 3),
    MON("월요일", 4),
    TUE("화요일", 5),
    WED("수요일", 6),
    THU("목요일", 0),
    FRI("금요일", 1),
    SAT("토요일", 2);

    private String day;
    private int dayOfWeek;

    DayOfWeek(String day, int dayOfWeek) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public String getDay() {
        return day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public static DayOfWeek of(int dayOfWeek) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.dayOfWeek == dayOfWeek) {
                return day;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.DAY_ERROR.getMessage());
    }

}
