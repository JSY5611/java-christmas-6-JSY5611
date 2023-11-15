package christmas.domain.event;

public enum ParticipationMoney {
    PARTICIPATION_MINIMUM_MONEY(10000),
    PRESNETATION_MINIMUM_MONEY(120000),
    INCREMENT_MONEY(100);

    private int money;

    ParticipationMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
