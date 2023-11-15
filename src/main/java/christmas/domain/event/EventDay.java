package christmas.domain.event;

import christmas.domain.day.DayOfWeek;

import java.util.List;

public enum EventDay {

    CHRISTMAS("크리스마스 디데이 할인", 1000),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    SPECIAL("특별 할인", 1000),
    PRESENTATION("증정 이벤트", 25000),
    SANTA_BADGE("산타", 20000),
    TREE_BADGE("트리", 10000),
    STAR_BADGE("별", 5000);


    private String name;
    private int eventPrice;

    private static String NO_BADGE = "없음";

    private static List<Integer> weekend = List.of(DayOfWeek.FRI.getDay(), DayOfWeek.SAT.getDay());

    EventDay(String name, int eventPrice) {
        this.name = name;
        this.eventPrice = eventPrice;
    }

    public static String findBadge(int totalBenefitPrice) {
        if(totalBenefitPrice >= SANTA_BADGE.getEventPrice()) {
            return SANTA_BADGE.getName();
        }
        if(totalBenefitPrice >= TREE_BADGE.getEventPrice()) {
            return TREE_BADGE.getName();
        }
        if(totalBenefitPrice >= STAR_BADGE.getEventPrice()) {
            return STAR_BADGE.getName();
        }
        return NO_BADGE;
    }


    public String getName() {
        return name;
    }

    public int getEventPrice() {
        return eventPrice;
    }
    public static EventDay findEventDay(int weekDay) {
        if(weekend.contains(weekDay)) {
            return WEEKEND;
        }
        return WEEKDAY;
    }

}
