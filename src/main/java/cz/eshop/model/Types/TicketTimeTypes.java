package cz.eshop.model.Types;

public enum TicketTimeTypes {
    MONTH(1),
    HALF_YEAR(6),
    YEAR(12);

    private int number;

    TicketTimeTypes(int number) {
        this.number = number;
    }

    public int Number() {
        return number;
    }
}
