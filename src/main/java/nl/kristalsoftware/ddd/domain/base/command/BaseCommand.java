package nl.kristalsoftware.ddd.domain.base.command;

import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateRepository;
import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventSaving;

import java.util.List;

public interface BaseCommand<R extends AggregateRepository, T extends BaseAggregateRoot> {

    List<DomainEventSaving<R>> handleCommand(T aggregate);

}
