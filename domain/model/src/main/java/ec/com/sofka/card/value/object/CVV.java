package ec.com.sofka.card.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class CVV implements ValueObject<String> {
    private final String value;

    private CVV(final String value) {
        this.value = validate(value);
    }

    public static CVV of(final String value) {
        return new CVV(value);
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
