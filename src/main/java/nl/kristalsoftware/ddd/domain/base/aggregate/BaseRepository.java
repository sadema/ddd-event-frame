package nl.kristalsoftware.ddd.domain.base.aggregate;

import nl.kristalsoftware.ddd.domain.base.command.BaseCommand;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventSaving;

import java.util.List;

public interface BaseRepository<R extends AggregateRepository, T extends BaseAggregateRoot> {
    List<DomainEventSaving<R>> sendCommand(BaseCommand<R,T> command);
}
