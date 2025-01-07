package ec.com.sofka.aggregate.value;

import ec.com.sofka.generic.util.Identity;

public class OperationId extends Identity {
    public OperationId() {
        super();
    }

    private OperationId(final String id) {
        super(id);
    }

    public static OperationId of(final String id) {
        return new OperationId(id);
    }
}