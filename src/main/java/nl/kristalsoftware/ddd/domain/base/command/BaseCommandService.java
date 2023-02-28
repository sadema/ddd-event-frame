package nl.kristalsoftware.ddd.domain.base.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateRepository;
import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;
import nl.kristalsoftware.ddd.domain.base.event.DomainEventSaving;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseCommandService<R extends AggregateRepository, T extends BaseAggregateRoot> {
    public List<DomainEventSaving<R>> sendCommand(BaseCommand<R,T> command, T aggregate) {
        return command.handleCommand(aggregate);
    }

}
