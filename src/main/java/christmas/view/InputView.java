package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_DAY_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";


    public static String InputVisitDay() {
        System.out.println(INPUT_DAY_VISIT_MESSAGE);
        return Console.readLine();
    }

    public static String InputMenu() {
        System.out.println(INPUT_MENU_MESSAGE);
        return Console.readLine();
    }
}
