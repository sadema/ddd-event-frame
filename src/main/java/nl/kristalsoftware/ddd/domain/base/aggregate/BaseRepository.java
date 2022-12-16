package nl.kristalsoftware.ddd.domain.base.aggregate;

import nl.kristalsoftware.ddd.domain.base.command.BaseCommand;

public interface BaseRepository<T extends AggregateRepository> {
    void sendCommand(BaseCommand<T> command);
}
