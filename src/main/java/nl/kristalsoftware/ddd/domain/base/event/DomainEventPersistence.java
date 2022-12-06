package nl.kristalsoftware.ddd.domain.base.event;

import nl.kristalsoftware.ddd.domain.base.aggregate.BaseContext;

public interface DomainEventPersistence<T extends BaseContext<T>> { // <V extends AggregateContext<T,U>,T extends BaseAggregateRoot<U>,U> {
    void save(T context);
}
