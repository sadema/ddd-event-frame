package nl.kristalsoftware.ddd.domain.base.aggregate;

import lombok.Getter;
import nl.kristalsoftware.ddd.domain.base.command.BaseCommand;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventLoading;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventSaving;
import nl.kristalsoftware.ddd.domain.base.event.EventsRepositoryPort;
import nl.kristalsoftware.ddd.domain.base.view.DocumentsRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRepository<C extends AggregateRepository, T extends BaseAggregateRoot<U>,U> implements BaseRepository<C> {

    @Getter
    private final T aggregate;
    private final EventsRepositoryPort<T> eventsRepositoryPort;
    private final DocumentsRepositoryPort<T> documentsRepositoryPort;
    private final List<DomainEventSaving<C>> domainEventList = new ArrayList<>();


    protected AggregateRepository(AggregateFactory<T,U> aggregateFactory, AggregateRepositoryService<T> aggregateRepositoryService) {
        this.aggregate = aggregateFactory.createAggregate();
        this.eventsRepositoryPort = aggregateRepositoryService.getEventsRepository();
        this.documentsRepositoryPort = aggregateRepositoryService.getDocumentsRepository();
    }

    protected AggregateRepository(U reference, AggregateFactory<T,U> aggregateFactory, AggregateRepositoryService<T> aggregateRepositoryService) {
        this.aggregate = aggregateFactory.createAggregate(reference);
        this.eventsRepositoryPort = aggregateRepositoryService.getEventsRepository();
        this.documentsRepositoryPort = aggregateRepositoryService.getDocumentsRepository();
        loadDomainEvents(aggregate);
        documentsRepositoryPort.createDocument(aggregate);
    }

    private void loadDomainEvents(T aggregate) {
        List<DomainEventLoading<T>> aggregateDomainEvents = findAllDomainEventsByReference(aggregate);
        loadDomainEvents(aggregateDomainEvents);
    }

    private List<DomainEventLoading<T>> findAllDomainEventsByReference(T aggregate) {
        return eventsRepositoryPort.findAllDomainEventsByReference(aggregate);
    }

    private void loadDomainEvents(List<DomainEventLoading<T>> aggregateDomainEvents) {
        aggregateDomainEvents.stream()
                .forEach(it -> it.load(getAggregate()));
    }

    @Transactional
    public void saveEvents() {
        saveAllEvents();
        commitDocumentChanges();
    }

    protected abstract C getDomainRepository();

    public void addEvent(DomainEventSaving<C> domainEvent) {
        domainEventList.add(domainEvent);
    }

    private void saveAllEvents() {
        domainEventList.stream().forEach(it -> {
            it.save(getDomainRepository());
        });
    }

    @Override
    public void sendCommand(BaseCommand<C> command) {
        command.handleCommand(getDomainRepository());
    }

    private void commitDocumentChanges() {
        documentsRepositoryPort.saveDocument();
    }

}
