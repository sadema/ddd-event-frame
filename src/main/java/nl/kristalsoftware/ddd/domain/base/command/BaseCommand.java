package nl.kristalsoftware.ddd.domain.base.command;

import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateRepository;

public interface BaseCommand<T extends AggregateRepository> {

    void handleCommand(T repository);

}
