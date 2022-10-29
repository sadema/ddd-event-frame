package nl.kristalsoftware.ddd.domain.base.aggregate;

import lombok.Getter;

public abstract class BaseAggregateRoot<T> {

    @Getter
    private final T reference;
    @Getter
    public final Long version;
    @Getter
    public final boolean existingAggregate;

//    protected BaseAggregateRoot(T reference) {
//        this.reference = reference;
//        this.version = 0L;
//        this.existingAggregate = false;
//    }

    protected BaseAggregateRoot(T reference, boolean existingAggregate) {
        this.reference = reference;
        this.version = 0L;
        this.existingAggregate = existingAggregate;
    }

    protected BaseAggregateRoot(T reference, Long version) {
        this.reference = reference;
        this.version = version;
        this.existingAggregate = true;
    }

    public abstract void saveDocument();
}
