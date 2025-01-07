package ec.com.sofka.customer.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class Phone implements ValueObject<String> {
    private final String value;

    private Phone(final String value) {
        this.value = validate(value);
    }

    public static Phone of(final String value) {
        return new Phone(value);
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
