package christmas.service;

import christmas.domain.DayOfWeek;
import christmas.domain.event.EventDay;

import java.util.HashMap;

import static christmas.domain.event.EventDay.findEventDay;

public class EventValidator {

    private int totalDiscountPrice;

    private HashMap<String, Integer> event;

    public EventValidator(int inputDay, int orderPrice) {
        event = new HashMap<>();
        this.totalDiscountPrice = 0;
        if(orderPrice >= 10000){
            validateEvent(inputDay, orderPrice);
        }
    }

    private void validateEvent(int inputDay, int orderPrice) {
        int christmasEvent = christmasEvent(inputDay);
        int weekdayEvent = weekdayEvent(inputDay);
        int weekendEvent = weekendEvent(inputDay);
        int specialEvent = specialEvent(inputDay);
        int presentationEvent = presentationEvent(orderPrice);

        checkEventAndAdd(EventDay.CHRISTMAS, christmasEvent);
        checkEventAndAdd(EventDay.WEEKDAY, weekdayEvent);
        checkEventAndAdd(EventDay.WEEKEND, weekendEvent);
        checkEventAndAdd(EventDay.SPECIAL, specialEvent);
        checkEventAndAdd(EventDay.PRESENTATION, presentationEvent);
    }

    private void checkEventAndAdd(EventDay eventDay, int getEventPrice) {
        if(getEventPrice != 0) {
            event.put(eventDay.getName(), getEventPrice);
        }
    }

    private String convertToWeekDay(int inputDay) {
        int dayOfWeek = inputDay % 7;
        DayOfWeek day = DayOfWeek.of(dayOfWeek);
        return day.getDay();
    }

    public int christmasEvent(int inputDay) {
        if(inputDay == 1) {
            return EventDay.CHRISTMAS.getEventPrice();
        }
        if(inputDay > 1 && inputDay <= 25) {
            return EventDay.CHRISTMAS.getEventPrice()
                    + ((inputDay - 1) * 100);
        }
        return 0;
    }
    private int weekdayEvent(int inputDay) {
        String weekDay = convertToWeekDay(inputDay);
        if(findEventDay(weekDay) == EventDay.WEEKDAY) {
            return EventDay.WEEKDAY.getEventPrice();
        }
        return 0;
    }

    private int weekendEvent(int inputDay) {
        String weekDay = convertToWeekDay(inputDay);
        if(findEventDay(weekDay) == EventDay.WEEKEND) {
            return EventDay.WEEKEND.getEventPrice();
        }
        return 0;
    }

    private int specialEvent(int inputDay) {
        String weekDay = convertToWeekDay(inputDay);
        if(weekDay.equals(DayOfWeek.SUN.getDay()) || inputDay == 25) {
            return EventDay.SPECIAL.getEventPrice();
        }
        return 0;
    }

    private int presentationEvent(int orderPrice) {
        if(orderPrice >= 120000) {
            return EventDay.PRESENTATION.getEventPrice();
        }
        return 0;
    }

    public HashMap<String, Integer> getEvent() {
        return event;
    }

}
