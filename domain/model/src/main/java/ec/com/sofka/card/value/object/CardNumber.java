package ec.com.sofka.card.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class CardNumber implements ValueObject<String> {
    private final String value;

    private CardNumber(final String value) {
        this.value = validate(value);
    }

    public static CardNumber of(final String value) {
        return new CardNumber(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value){
        //TODO Validaciones
        return value;
    }

}
