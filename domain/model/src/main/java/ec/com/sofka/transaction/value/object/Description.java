package ec.com.sofka.transaction.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Description implements ValueObject<String> {
    private final String value;

    private Description(final String value) {
        this.value = validate(value);
    }

    public static Description of(final String value) {
        return new Description(value);
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
