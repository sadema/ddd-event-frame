package nl.kristalsoftware.ddd.domain.base.view;

import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;

public interface DocumentsRepositoryPort<T extends BaseAggregateRoot> {
    void createDocument(T aggregate);

    void saveDocument();
}
