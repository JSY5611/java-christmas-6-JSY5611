package christmas.service;

import christmas.domain.event.EventDay;
import christmas.domain.menu.Menu;

import java.util.HashMap;

public class BenefitValidator {

    private HashMap<String, Integer> event;
    private HashMap<Menu, Integer> orderMenu;
    private HashMap<String, Integer> benefit;
    public BenefitValidator(EventValidator eventValidator, MenuValidator menuValidator) {
        benefit = new HashMap<>();
        this.event = eventValidator.getEvent();
        this.orderMenu = menuValidator.getMenu();
        calculateBenefit();
    }

    public void calculateBenefit() {
        calculateWeekdayBenefit();
        calculateWeekendBenefit();
        calculateSpecialBenefit();
        calculateChristmasBenefit();
        calculatePresentationBenefit();
    }

    private void calculatePresentationBenefit() {
        if(event.containsKey(EventDay.PRESENTATION.getName())) {
            benefit.put(EventDay.PRESENTATION.getName(), event.get(EventDay.PRESENTATION.getName()));
        }
    }

    private void calculateChristmasBenefit() {
        if(event.containsKey(EventDay.CHRISTMAS.getName())) {
            benefit.put(EventDay.CHRISTMAS.getName(), event.get(EventDay.CHRISTMAS.getName()));
        }
    }

    private void calculateSpecialBenefit() {
        if(event.containsKey(EventDay.SPECIAL.getName())) {
            benefit.put(EventDay.SPECIAL.getName(), event.get(EventDay.SPECIAL.getName()));
        }
    }

    private void calculateWeekendBenefit() {
        if(event.containsKey(EventDay.WEEKEND.getName())) {
            int totalDiscountPrice = findMainDishTotalPrice();
            benefit.put(EventDay.WEEKEND.getName(), totalDiscountPrice);
        }
    }

    private int findMainDishTotalPrice() {
        int totalDiscountMainPrice = 0;
        for(Menu menu : orderMenu.keySet()) {
            if(menu.ordinal() > 2 && menu.ordinal() < 7) {
                totalDiscountMainPrice += EventDay.WEEKEND.getEventPrice() * orderMenu.get(menu);
            }
        }
        return totalDiscountMainPrice;
    }

    private void calculateWeekdayBenefit() {
        if(event.containsKey(EventDay.WEEKDAY.getName())) {
            int totalDiscountPrice = findDessertTotalPrice();
            benefit.put(EventDay.WEEKDAY.getName(), totalDiscountPrice);
        }
    }

    private int findDessertTotalPrice() {
        int totalDiscountDessertPrice = 0;
        for(Menu menu : orderMenu.keySet()) {
            if(menu.ordinal() == 7 || menu.ordinal() == 8) {
                totalDiscountDessertPrice += EventDay.WEEKDAY.getEventPrice() * orderMenu.get(menu);
            }
        }
        return totalDiscountDessertPrice;
    }

    public HashMap<String, Integer> getBenefit() {
        return benefit;
    }

    public int getTotalBenefitPrice() {
        return benefit.entrySet().stream()
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public String calculateBadge(int totalBenefitPrice) {
        return EventDay.findBadge(totalBenefitPrice);

    }

    public boolean isBenefitPresentation() {
        return benefit.containsKey(EventDay.PRESENTATION.getName());
    }


}
