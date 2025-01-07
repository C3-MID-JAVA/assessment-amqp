package ec.com.sofka.account.value;

import ec.com.sofka.generic.util.Identity;

public class AccountId extends Identity {
    public AccountId() {
        super();
    }

    private AccountId(final String id) {
        super(id);
    }

    public static AccountId of(final String id) {
        return new AccountId(id);
    }
}