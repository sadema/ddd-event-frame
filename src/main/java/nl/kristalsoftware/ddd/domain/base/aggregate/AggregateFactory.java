package nl.kristalsoftware.ddd.domain.base.aggregate;

public interface AggregateFactory<T,U> {
    T createAggregate();
    T createAggregate(U reference);
}
