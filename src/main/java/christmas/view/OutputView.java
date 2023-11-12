package christmas.view;

import christmas.domain.menu.Menu;

import java.util.HashMap;

public class OutputView {
    private static final String PLANNER_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_EVENT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_LIST_MESSAGE = "<주문 메뉴>";
    private static final String ORDER_MENU_LIST_FORMAT = "%s %d개";
    private static final String DISCOUNT_BEFORE_TOTAL_PRICE_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String PRESENTATION_MESSAGE = "\n<증정 메뉴>";
    private static final String BENEFIT_LIST = "\n<혜택 내역>";
    private static final String BENEFIT_LIST_FORMAT = "%s: -%,d원";
    private static final String BENEFIT_TOTAL_PRICE_MESSAGE = "\n<총혜택 금액>";
    private static final String DISCOUNT_TOTAL_FORMAT = "-%,d원";
    private static final String DISCOUNT_AFTER_TOTAL_PRICE_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE = "\n<12월 이벤트 배지>";
    private static final String EVENT_BADGE_FORMAT = "%s";
    private static final String NO_BENEFIT_MESSAGE = "없음";

    public static void printPlannerMessage() {
        System.out.println(PLANNER_MESSAGE);
    }
    public static void printPreviewEventMessage(int dayVisit) {
        System.out.println(String.format(PREVIEW_EVENT_MESSAGE, dayVisit));
    }
    public static void printOrderMenuListMessage() {
        System.out.println(ORDER_MENU_LIST_MESSAGE);
    }
    public static void printOrderMenuList(HashMap<Menu, Integer> menu) {
        menu.forEach(OutputView::printOrderMenuList);
    }
    public static void printOrderMenuList(Menu menu, Integer count) {
        System.out.println(String.format(ORDER_MENU_LIST_FORMAT, menu.getName(), count));
    }

    public static void printDiscountBeforeTotalPriceMessage() {
        System.out.println(DISCOUNT_BEFORE_TOTAL_PRICE_MESSAGE);
    }
    public static void printPrice(int totalPrice) {
        System.out.println(String.format(PRICE_FORMAT, totalPrice));
    }
    public static void printPresentationMessage() {
        System.out.println(PRESENTATION_MESSAGE);
    }
    public static void printBenefitList() {
        System.out.println(BENEFIT_LIST);
    }
    public static void printBenefitList(HashMap<String, Integer> benefit) {
        benefit.forEach(OutputView::printBenefitList);
    }
    private static void printBenefitList(String benefitName, Integer benefitPrice) {
        System.out.println(String.format(BENEFIT_LIST_FORMAT, benefitName, benefitPrice));
    }
    public static void printBenefitTotalPriceMessage() {
        System.out.println(BENEFIT_TOTAL_PRICE_MESSAGE);
    }
    public static void printDiscountTotalPrice(int discountTotalPrice) {
        System.out.println(String.format(DISCOUNT_TOTAL_FORMAT, discountTotalPrice));
    }
    public static void printDiscountAfterTotalPriceMessage() {
        System.out.println(DISCOUNT_AFTER_TOTAL_PRICE_MESSAGE);
    }
    public static void printEventBadgeMessage() {
        System.out.println(EVENT_BADGE_MESSAGE);
    }
    public static void printEventBadge(String eventBadge) {
        System.out.println(String.format(EVENT_BADGE_FORMAT, eventBadge));
    }
    public static void printError(String message) {
        System.out.println(message);
    }
    public static void printNoBenefitMessage() {
        System.out.println(NO_BENEFIT_MESSAGE);
    }
}
