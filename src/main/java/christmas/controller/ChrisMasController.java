package christmas.controller;

import christmas.domain.ErrorMessage;
import christmas.domain.event.EventDay;
import christmas.domain.menu.Menu;
import christmas.service.BenefitValidator;
import christmas.service.DayValidator;
import christmas.service.EventValidator;
import christmas.service.MenuValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;

import static christmas.view.InputView.InputMenu;
import static christmas.view.InputView.InputVisitDay;
import static christmas.view.OutputView.*;

public class ChrisMasController {
    private DayValidator dayValidator;
    private MenuValidator menuValidator;
    private EventValidator eventValidator;
    private BenefitValidator benefitValidator;
    private HashMap<String, Integer> benefit;

    public ChrisMasController() {
        this.benefit = new HashMap<>();
    }

    public void run() {
        printPlannerMessage();
        InputDay();
        InputMenuAndCount();
        printPreviewEventMessage(dayValidator.getDay());
        calculateTotalEvent();
        printResult();
    }

    private void printResult() {
        printOrderMenuListMessage();
        printOrderMenuList(menuValidator.getMenu());
        printDiscountBeforeTotalPriceMessage();
        printPrice(menuValidator.getTotalPrice());
        printAllBenefitMessage();
        printDiscountAfterTotalPriceMessage();
        printPrice(getDiscountTotalPrice());
        printEventBadgeMessage();
        printEventBadge(benefitValidator.calculateBadge(benefitValidator.getTotalBenefitPrice()));
    }

    private void printAllBenefitMessage() {
        if(benefit.isEmpty()) {
            printNoBenefit();
        }
        if(!benefit.isEmpty()) {
            printPresentationMessage();
            printOrderMenuList(Menu.CHAMPAGNE, 1);
            printBenefitList();
            printBenefitList(benefit);
            printBenefitTotalPriceMessage();
            printDiscountTotalPrice(benefitValidator.getTotalBenefitPrice());
        }
    }

    private void printNoBenefit() {
        printPresentationMessage();
        printNoBenefitMessage();
        printBenefitList();
        printNoBenefitMessage();
        printBenefitTotalPriceMessage();
        printPrice(0);
    }

    private void calculateTotalEvent() {
        eventValidator = new EventValidator(dayValidator.getDay(), menuValidator.getTotalPrice());
        benefitValidator = new BenefitValidator(eventValidator, menuValidator);
        benefit = benefitValidator.getBenefit();
    }

    private void InputDay() {
        try {
            this.dayValidator = new DayValidator(InputVisitDay());
        } catch (IllegalArgumentException e) {
            printError(ErrorMessage.DAY_ERROR.getMessage());
            InputDay();
        }
    }

    private void InputMenuAndCount() {
        try {
            this.menuValidator = new MenuValidator(InputMenu());
        } catch (IllegalArgumentException e) {
            printError(ErrorMessage.MENU_ERROR.getMessage());
            InputMenuAndCount();
        }
    }

    private int getDiscountTotalPrice() {
        if (benefitValidator.isBenefitPresentation()) {
            return menuValidator.getTotalPrice() - benefitValidator.getTotalBenefitPrice()
                    + EventDay.PRESENTATION.getEventPrice();
        }

        return menuValidator.getTotalPrice() - benefitValidator.getTotalBenefitPrice();
    }

}
