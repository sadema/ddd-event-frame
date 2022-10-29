package nl.kristalsoftware.ddd.domain.base.aggregate;

import lombok.Getter;
import nl.kristalsoftware.ddd.domain.base.PersistenceFactoryPort;
import nl.kristalsoftware.ddd.domain.base.command.BaseCommand;
import nl.kristalsoftware.ddd.domain.base.event.BaseDomainEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateContext<T extends BaseAggregateRoot<U>,U> {

    private final AggregateFactory<T,U> aggregateFactory;
    @Getter
    private final List<BaseDomainEvent<T>> domainEventList = new ArrayList<>();

    protected AggregateContext(AggregateFactory<T,U> aggregateFactory) {
        this.aggregateFactory = aggregateFactory;
    }

    public void loadDomainEvents(T aggregate) {
        List<BaseDomainEvent<T>> aggregateDomainEvents = getPersistenceFactoryPort().findAllDomainEventsByReference(aggregate);
        loadDomainEvents(aggregateDomainEvents);
    }

    private void loadDomainEvents(List<BaseDomainEvent<T>> aggregateDomainEvents) {
        aggregateDomainEvents.stream()
                .forEach(it -> it.load(getAggregate()));
    }

    public abstract T getAggregate();

    public abstract PersistenceFactoryPort<T> getPersistenceFactoryPort();

    public void sendCommand(BaseCommand<T,U> command) throws AggregateNotFoundException {
        command.handleCommand(this);
    }

    public void addEvent(BaseDomainEvent<T> event) {
        domainEventList.add(event);
    }

    @Transactional
    public void saveEvents() throws AggregateNotFoundException {
        createPersistenceProcessor();
        domainEventList.stream().forEach(it -> {
            it.save(getAggregate());
        });
        getAggregate().saveDocument();
    }

    protected abstract void createPersistenceProcessor() throws AggregateNotFoundException;
}
