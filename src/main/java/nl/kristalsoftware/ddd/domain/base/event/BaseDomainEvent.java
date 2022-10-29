package nl.kristalsoftware.ddd.domain.base.event;

public interface BaseDomainEvent<T> {
    void load(T aggregate);

    void save(T aggregate);

}
