package ec.com.sofka.generic.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class DomainActionsContainer {
    protected Set<Consumer<? super DomainEvent>> domainActions = new HashSet<>();

    public void addDomainActions(Consumer<? extends DomainEvent> action) {
        domainActions.add((Consumer<? super DomainEvent>) action );
    }
}
