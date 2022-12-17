package nl.kristalsoftware.ddd.domain.base.event;

import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateRepository;

public interface DomainEventSaving<T extends AggregateRepository> {
    void save(T repository);
}
