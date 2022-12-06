package nl.kristalsoftware.ddd.domain.base.aggregate;

public class AggregateNotFoundException extends Throwable {
    public AggregateNotFoundException(String message) {
        super(message);
    }
}
