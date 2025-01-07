package ec.com.sofka.aggregate.value;

import ec.com.sofka.generic.util.Identity;

public class UserId extends Identity {
    public UserId() {
        super();
    }

    private UserId(final String id) {
        super(id);
    }

    public static UserId of(final String id) {
        return new UserId(id);
    }
}