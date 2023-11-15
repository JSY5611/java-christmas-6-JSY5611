package christmas.service;

import christmas.domain.day.DayOfWeek;
import christmas.domain.event.EventDay;
import christmas.domain.event.ParticipationMoney;

import java.util.HashMap;

import static christmas.domain.event.EventDay.findEventDay;

public class EventValidator {

    private int totalDiscountPrice;
    private HashMap<String, Integer> event;
    private final int firstDay = 1;
    private final int christmas = 25;

    public EventValidator(int inputDay, int orderPrice) {
        event = new HashMap<>();
        this.totalDiscountPrice = 0;
        if(orderPrice >= ParticipationMoney.PARTICIPATION_MINIMUM_MONEY.getMoney()) {
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

    private int convertToWeekDay(int inputDay) {
        int dayOfWeek = inputDay % 7;
        DayOfWeek day = DayOfWeek.of(dayOfWeek);
        return day.getDay();
    }

    public int christmasEvent(int inputDay) {
        if(inputDay == firstDay) {
            return EventDay.CHRISTMAS.getEventPrice();
        }
        if(inputDay > firstDay && inputDay <= christmas) {
            return EventDay.CHRISTMAS.getEventPrice()
                    + ((inputDay - firstDay) * ParticipationMoney.INCREMENT_MONEY.getMoney());
        }
        return 0;
    }
    private int weekdayEvent(int inputDay) {
        int weekDay = convertToWeekDay(inputDay);
        if(findEventDay(weekDay) == EventDay.WEEKDAY) {
            return EventDay.WEEKDAY.getEventPrice();
        }
        return 0;
    }

    private int weekendEvent(int inputDay) {
        int weekDay = convertToWeekDay(inputDay);
        if(findEventDay(weekDay) == EventDay.WEEKEND) {
            return EventDay.WEEKEND.getEventPrice();
        }
        return 0;
    }

    private int specialEvent(int inputDay) {
        int weekDay = convertToWeekDay(inputDay);
        if(weekDay == (DayOfWeek.SUN.getDay()) || inputDay == christmas) {
            return EventDay.SPECIAL.getEventPrice();
        }
        return 0;
    }

    private int presentationEvent(int orderPrice) {
        if(orderPrice >= ParticipationMoney.PRESNETATION_MINIMUM_MONEY.getMoney()) {
            return EventDay.PRESENTATION.getEventPrice();
        }
        return 0;
    }

    public HashMap<String, Integer> getEvent() {
        return event;
    }

}
