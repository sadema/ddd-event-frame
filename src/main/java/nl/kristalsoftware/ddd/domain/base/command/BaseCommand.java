package nl.kristalsoftware.ddd.domain.base.command;

import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateContext;
import nl.kristalsoftware.ddd.domain.base.aggregate.AggregateNotFoundException;
import nl.kristalsoftware.ddd.domain.base.aggregate.BaseAggregateRoot;

public interface BaseCommand<T extends BaseAggregateRoot<U>,U> {

    void handleCommand(AggregateContext<T,U> context) throws AggregateNotFoundException;

}
