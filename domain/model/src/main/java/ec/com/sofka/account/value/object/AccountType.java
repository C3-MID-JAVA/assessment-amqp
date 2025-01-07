package ec.com.sofka.account.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class AccountType implements ValueObject<String> {
    private final String value;

    private AccountType(final String value) {
        this.value = validate(value);
    }

    public static AccountType of(final String value) {
        return new AccountType(value);
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
