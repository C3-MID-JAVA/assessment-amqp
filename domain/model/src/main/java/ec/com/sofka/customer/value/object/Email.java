package ec.com.sofka.customer.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Email implements ValueObject<String> {
    private final String value;

    private Email(final String value) {
        this.value = validate(value);
    }

    public static Email of(final String value) {
        return new Email(value);
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
