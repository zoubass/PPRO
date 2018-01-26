package cz.eshop.model.Types;

public enum StripeTypes{
    ZERO(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private int number;
    StripeTypes(int number){this.number = number;}

    public int Number(){
        return number;
    }
}
