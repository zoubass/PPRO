package cz.eshop.model.Types;

public enum TicketPointTypes {
    ONE_TIME(1),
    TEN_TIMES(10),
    TWENTY_TIMES(20),
    THIRTY_TIMES(30);

    private int number;

    TicketPointTypes(int number) {
        this.number = number;
    }

    public int Number() {
        return number;
    }
}
