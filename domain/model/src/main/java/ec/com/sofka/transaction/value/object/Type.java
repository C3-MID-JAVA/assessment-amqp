package ec.com.sofka.transaction.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Type implements ValueObject<String> {
    private final String value;

    private Type(final String value) {
        this.value = validate(value);
    }

    public static Type of(final String value) {
        return new Type(value);
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
