package nl.kristalsoftware.ddd.domain.base.aggregate;

import nl.kristalsoftware.ddd.domain.base.PersistenceFactoryPort;
import nl.kristalsoftware.ddd.domain.base.event.BaseDomainEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AggregateContext<T extends BaseAggregateRoot<U>,U> {

    private final AggregateFactory<T,U> aggregateFactory;
    private final PersistenceFactoryPort<T> persistenceFactoryPort;

    protected AggregateContext(AggregateFactory<T,U> aggregateFactory, PersistenceFactoryPort<T> persistenceFactoryPort) {
        this.aggregateFactory = aggregateFactory;
        this.persistenceFactoryPort = persistenceFactoryPort;
    }

    public void loadDomainEvents(T aggregate) {
        List<BaseDomainEvent<T>> aggregateDomainEvents = persistenceFactoryPort.findAllDomainEventsByReference(aggregate);
        loadDomainEvents(aggregateDomainEvents);
    }

    private void loadDomainEvents(List<BaseDomainEvent<T>> aggregateDomainEvents) {
        aggregateDomainEvents.stream()
                .forEach(it -> it.load(getAggregate()));
    }

    public abstract T getAggregate();

    @Transactional
    public void saveEvents() {
        saveAllEvents();
        saveDocument();
    }

    protected abstract void saveDocument();

    protected abstract void saveAllEvents();
}
