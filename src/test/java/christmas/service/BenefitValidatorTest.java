package christmas.service;

import christmas.domain.event.EventDay;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BenefitValidatorTest {

    private MenuValidator menuValidator;
    private DayValidator dayValidator;
    private EventValidator eventValidator;
    private BenefitValidator benefitValidator;

    private static final String SUNDAY = "10"; //특별, 평일
    private static final String FRIDAY = "29";// 주말
    private static final String CHRISTMAS = "25"; // 특별, 평일

    private static final String MENU = "양송이수프-1,티본스테이크-2,초코케이크-1,제로콜라-1";
    private static final String MENU2 = "타파스-1,시저샐러드-1,바비큐립-1,해산물파스타-1,아이스크림-1,레드와인-1";

    private static final String MENU3 = "양송이수프-1,제로콜라-1";


    @Test
    void 혜택_없음_테스트() {
        menuValidator = new MenuValidator(MENU3);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        benefitValidator = new BenefitValidator(eventValidator, menuValidator);
        HashMap<String, Integer> benefit = benefitValidator.getBenefit();

        assertEquals(0, benefit.size());

    }

    @Test
    void 산타_특별_평일() {
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        benefitValidator = new BenefitValidator(eventValidator, menuValidator);
        HashMap<String, Integer> benefit = benefitValidator.getBenefit();

        assertEquals("산타", benefitValidator.calculateBadge(menuValidator.getTotalPrice()));
    }

    @Test
    void 트리_테스트 () {
        int totalBenefit = 10000;
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        benefitValidator = new BenefitValidator(eventValidator, menuValidator);
        assertEquals("트리", benefitValidator.calculateBadge(totalBenefit));
    }

    @Test
    void 별_테스트() {
        int totalBenefit = 5000;
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        BenefitValidator benefitValidator = new BenefitValidator(eventValidator, menuValidator);
        assertEquals("별", benefitValidator.calculateBadge(totalBenefit));
    }

    @Test
    void 총혜택_테스트() {
        menuValidator = new MenuValidator(MENU);
        dayValidator = new DayValidator(SUNDAY);
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        benefitValidator = new BenefitValidator(eventValidator, menuValidator);

        assertEquals(29923, benefitValidator.getTotalBenefitPrice());

    }

}