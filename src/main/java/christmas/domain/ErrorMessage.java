package christmas.domain;

public enum ErrorMessage {
    MENU_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_ERROR("[ERROR] 주문하신 메뉴의 수량이 20개 이상입니다. 다시 입력해 주세요."),
    DAY_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
