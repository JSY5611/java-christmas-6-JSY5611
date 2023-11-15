package christmas.service;

import christmas.error.ErrorMessage;

public class DayValidator {

    private int day;
    private static final int firstDay = 1;
    private static final int lastDay = 31;

    public DayValidator(String inputDay) {
        this.day = Integer.parseInt(inputDay);
        validateDay();
    }

    private void validateDay() {
        if (!isValidDay(day)) {
            throw new IllegalArgumentException(ErrorMessage.DAY_ERROR.getMessage());
        }
    }

    private static boolean isValidDay(int day) {
        return day >= firstDay && day <= lastDay;
    }

    public int getDay() {
        return day;
    }

}
