package nl.kristalsoftware.ddd.domain.base.command;

public interface BaseCommand<T> {

    void handleCommand(T context);

}
