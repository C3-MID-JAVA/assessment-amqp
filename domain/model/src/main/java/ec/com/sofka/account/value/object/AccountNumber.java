package ec.com.sofka.account.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class AccountNumber implements ValueObject<String> {
    private final String value;

    private AccountNumber(final String value) {
        this.value = validate(value);
    }

    public static AccountNumber of(final String value) {
        return new AccountNumber(value);
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
