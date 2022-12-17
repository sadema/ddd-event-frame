package nl.kristalsoftware.ddd.domain.base.aggregate;

import lombok.Getter;
import nl.kristalsoftware.ddd.domain.base.command.BaseCommand;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventLoading;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventSaving;
import nl.kristalsoftware.ddd.domain.base.event.EventsRepositoryPort;
import nl.kristalsoftware.ddd.domain.base.view.DocumentsRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AggregateRepository<R extends AggregateRepository, T extends BaseAggregateRoot<U>,U> implements BaseRepository<R,T> {

    @Getter
    private final T aggregate;
    private final EventsRepositoryPort<T> eventsRepositoryPort;
    private final DocumentsRepositoryPort<T> documentsRepositoryPort;
//    @Getter
//    private final List<DomainEventSaving<C>> domainEventList = new ArrayList<>();


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
    public void saveEvents(List<DomainEventSaving<R>> domainEventList) {
        saveAllEvents(domainEventList);
        commitDocumentChanges();
    }

    protected abstract R getDomainRepository();

//    public void addEvent(DomainEventSaving<C> domainEvent) {
//        domainEventList.add(domainEvent);
//    }

    private void saveAllEvents(List<DomainEventSaving<R>> domainEventList) {
        domainEventList.stream().forEach(it -> {
            it.save(getDomainRepository());
        });
    }

    @Override
    public List<DomainEventSaving<R>> sendCommand(BaseCommand<R,T> command) {
        return command.handleCommand(getAggregate());
    }

    private void commitDocumentChanges() {
        documentsRepositoryPort.saveDocument();
    }

}
