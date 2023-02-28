package nl.kristalsoftware.ddd.domain.base.aggregate;

import nl.kristalsoftware.ddd.domain.base.event.EventsRepositoryPort;
import nl.kristalsoftware.ddd.domain.base.view.DocumentsRepositoryPort;

public interface AggregateRepositoryService<T extends BaseAggregateRoot> {
    EventsRepositoryPort<T> getEventsRepository();

    DocumentsRepositoryPort<T> getDocumentsRepository();

}
