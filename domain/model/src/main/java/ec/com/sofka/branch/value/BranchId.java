package ec.com.sofka.branch.value;

import ec.com.sofka.generic.util.Identity;

public class BranchId extends Identity {
    public BranchId() {
        super();
    }

    private BranchId(final String id) {
        super(id);
    }

    public static BranchId of(final String id) {
        return new BranchId(id);
    }
}