package ec.com.sofka.gateway;

import ec.com.sofka.generic.domain.DomainEvent;

import java.util.List;

public interface EventStore {
    public interface IEventStore {
        DomainEvent save(DomainEvent event);
        List<DomainEvent> findAggregate(String aggregateId);
        List<DomainEvent> findAllAggregates();
    }
}
