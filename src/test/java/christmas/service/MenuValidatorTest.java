package christmas.service;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MenuValidatorTest {

    public static final String MENU = "티본스테이크-1,바비큐립-1";
    public static final String MENU2 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

    @Test
    void 메뉴_입력() {
        MenuValidator menuValidator = new MenuValidator(MENU2);
        HashMap<Menu, Integer> menu = menuValidator.getMenu();
        assertEquals(4, menu.size());
        assertEquals(1, menu.get(Menu.T_BONE_STEAK));
        assertEquals(1, menu.get(Menu.BARBECUE_RIB));
        assertEquals(2, menu.get(Menu.CHOCOLATE_CAKE));
        assertEquals(1, menu.get(Menu.ZERO_COLA));

    }

    @Test
    void 메뉴_총_가격() {
        MenuValidator menuValidator = new MenuValidator(MENU);
        assertEquals(109000, menuValidator.getTotalPrice());
    }

    @Test
    void 메뉴_중복_예외처리() {
        String DuplicateMenu = "티본스테이크-1,바비큐립-1,티본스테이크-1";
        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(DuplicateMenu));
    }

    @Test
    void 음료_단독_주문_예외처리() {
        String DrinkOnly = "제로콜라-1";
        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(DrinkOnly));

    }

    @Test
    void 음료_2개만_주문_예외처리() {
        String DrinkOnly2 = "제로콜라-1,샴페인-1";
        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(DrinkOnly2));

    }

    @Test
    void 음료_3개_주문_예외처리() {
        String DrinkOnly3 = "제로콜라-1,샴페인-1,레드와인-1";
        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(DrinkOnly3));
    }

    @Test
    void 메뉴_입력_형식_예외처리() {
        String input = "티본스테이크 1개, 바비큐립 1개";
        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(input));
    }

    @Test
    void 메뉴_개수_초과_예외처리() {
        String input = "티본스테이크-21";

        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(input));
    }

    @Test
    void 메뉴_개수_음수_예외처리() {
        String input = "티본스테이크--1";

        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(input));
    }

    @Test
    void 메뉴_개수_0개_예외처리() {
        String input = "티본스테이크-0";

        assertThrows(IllegalArgumentException.class, ()
                -> new MenuValidator(input));
    }


}