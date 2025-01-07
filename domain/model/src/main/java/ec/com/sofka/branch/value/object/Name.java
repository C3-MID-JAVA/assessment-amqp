package ec.com.sofka.branch.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Name implements ValueObject<String> {
    private final String value;

    private Name(final String value) {
        this.value = validate(value);
    }

    public static Name of(final String value) {
        return new Name(value);
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
