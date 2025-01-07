package ec.com.sofka.card.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class CardType implements ValueObject<String> {
    private final String value;

    private CardType(final String value) {
        this.value = validate(value);
    }

    public static CardType of(final String value) {
        return new CardType(value);
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
