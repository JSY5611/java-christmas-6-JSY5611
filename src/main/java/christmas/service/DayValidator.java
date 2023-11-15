package christmas.service;

import christmas.error.ErrorMessage;

public class DayValidator {

    private int day;

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
        return day >= 1 && day <= 31;
    }

    public int getDay() {
        return day;
    }

}
