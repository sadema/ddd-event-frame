package nl.kristalsoftware.ddd.domain.base.event;

import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;

import java.util.List;

public interface EventsRepositoryPort<T extends BaseAggregateRoot> {
    List<DomainEventLoading<T>> findAllDomainEventsByReference(T aggregate);
}
