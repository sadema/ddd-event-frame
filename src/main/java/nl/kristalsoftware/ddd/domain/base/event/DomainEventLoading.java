package nl.kristalsoftware.ddd.domain.base.event;

import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;

public interface DomainEventLoading<T extends BaseAggregateRoot> {
    void load(T aggregate);
}
