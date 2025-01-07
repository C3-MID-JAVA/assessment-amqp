package ec.com.sofka.eventAdapter;

import ec.com.sofka.generic.domain.DomainEvent;

import java.util.Comparator;
import java.util.List;

public class EventAdapter {

    @Override
    public DomainEvent save(DomainEvent event) {
        EventEntity e = new EventEntity(
                event.getEventId(),
                event.getAggregateRootId(),
                event.getEventType(),
                EventEntity.wrapEvent(event, mapper),
                event.getWhen().toString(),
                event.getVersion()

        );
        repository.save(e);
        return event;
    }

    @Override
    public List<DomainEvent> findAggregate(String aggregateId) {
        List<EventEntity> entities = repository.findByAggregateId(aggregateId);
        return entities.stream()
                .map(eventEntity -> eventEntity.deserializeEvent(mapper))
                .sorted(Comparator.comparing(DomainEvent::getVersion))
                .toList();
    }

    @Override
    public List<DomainEvent> findAllAggregates() {
        return repository.findAll().stream()
                .map(eventEntity ->eventEntity.deserializeEvent(mapper))
                .sorted(Comparator.comparing(DomainEvent::getAggregateRootId)
                        .thenComparing(DomainEvent::getVersion, Comparator.reverseOrder()))
                .toList();
    }
}
