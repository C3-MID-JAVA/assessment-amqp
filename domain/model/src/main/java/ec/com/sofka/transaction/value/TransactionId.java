package ec.com.sofka.transaction.value;

import ec.com.sofka.generic.util.Identity;

public class TransactionId extends Identity {
    public TransactionId() {
        super();
    }

    private TransactionId(final String id) {
        super(id);
    }

    public static TransactionId of(final String id) {
        return new TransactionId(id);
    }
}