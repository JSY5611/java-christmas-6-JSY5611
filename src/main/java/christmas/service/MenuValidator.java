package christmas.service;

import christmas.domain.ErrorMessage;
import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.sum;


public class MenuValidator {

    private final HashMap<Menu, Integer> menu;
    private int count;
    private int totalCount;

    public MenuValidator(String inputMenu) {
        menu = new HashMap<>();
        convertMenuHashMap(inputMenu);
        validate();
    }

    public void validate() {
        validateOrderMenu();
        validateMenuCount();
    }

    private void convertMenuHashMap(String inputMenu) {
        try {
            String[] menu = inputMenu.split(",");

            for (String menuCount : menu) {
                String[] menuAndCount = menuCount.split("-");
                String menuName = menuAndCount[0];
                count = Integer.parseInt(menuAndCount[1]);
                validateDuplicateMenu(menuName);
                validateCheckMenuCount(count);
                totalCount += count;
                this.menu.put(Menu.findMenu(menuName), count);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR.getMessage());
        }

    }

    private void validateCheckMenuCount(int count) {
        if(count < 0) throw new IllegalArgumentException(ErrorMessage.MENU_ERROR.getMessage());
    }

    private void validateDuplicateMenu(String menuName) {
        if (menu.containsKey(Menu.findMenu(menuName))) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR.getMessage());
        }
    }

    private void validateOrderMenu() {
        if (isDrinkOrder(menu)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR.getMessage());
        }
    }

    private static boolean isDrinkOrder(HashMap<Menu, Integer> order) {
        int totalDrink = order.entrySet().stream()
                .filter(menu -> menu.getKey().isDrink())
                .mapToInt(Map.Entry::getValue)
                .sum();
        int totalMenu = order.values().stream().mapToInt(Integer::intValue).sum();
        return totalMenu - totalDrink == 0;
    }

    private void validateMenuCount() {
        if (totalCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.MENU_COUNT_ERROR.getMessage());
        }
    }

    public HashMap<Menu, Integer> getMenu() {
        return menu;
    }

    public int getTotalPrice() {
        return menu.entrySet().stream()
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();
    }

}
