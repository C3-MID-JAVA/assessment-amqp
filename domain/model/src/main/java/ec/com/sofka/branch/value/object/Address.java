package ec.com.sofka.branch.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Address implements ValueObject<String> {
    private final String value;

    private Address(final String value) {
        this.value = validate(value);
    }

    public static Address of(final String value) {
        return new Address(value);
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
