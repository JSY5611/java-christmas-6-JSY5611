package christmas.service;

import christmas.domain.event.EventDay;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventValidatorTest {

    private MenuValidator menuValidator;
    private DayValidator dayValidator;
    private EventValidator eventValidator;

    private static final String SUNDAY = "10"; //특별, 평일
    private static final String FRIDAY = "29";// 주말
    private static final String CHRISTMAS = "25"; // 특별, 평일

    private static final String MENU = "양송이수프-1,티본스테이크-1,초코케이크-1,제로콜라-1";
    private static final String MENU2 = "타파스-1,시저샐러드-1,바비큐립-1,해산물파스타-1,아이스크림-1,레드와인-1";

    @Test
    void 평일_특별_크리스마스디데이_테스트() {
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        HashMap<String, Integer> event = eventValidator.getEvent();

        assertEquals(3, event.size());
        assertEquals(2023, event.get(EventDay.WEEKDAY.getName()));
        assertEquals(1000, event.get(EventDay.SPECIAL.getName()));
        assertEquals(1900, event.get(EventDay.CHRISTMAS.getName()));

    }

    @Test
    void 주말_테스트() {
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(FRIDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        HashMap<String, Integer> event = eventValidator.getEvent();

        assertEquals(1, event.size());
        assertEquals(2023, event.get(EventDay.WEEKEND.getName()));
        assertEquals("주말 할인", event.keySet().stream().filter(key -> key.equals(EventDay.WEEKEND.getName())).findFirst().get());
    }





}