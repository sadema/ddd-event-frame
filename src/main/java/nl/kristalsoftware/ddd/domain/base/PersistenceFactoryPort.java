package nl.kristalsoftware.ddd.domain.base;

import nl.kristalsoftware.ddd.domain.base.event.BaseDomainEvent;

import java.util.List;

public interface PersistenceFactoryPort<T> {
    List<BaseDomainEvent<T>> findAllDomainEventsByReference(T aggregate);
}
