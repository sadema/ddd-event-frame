package nl.kristalsoftware.ddd.domain.base;

import java.util.UUID;

public class TinyUUIDType extends TinyType<UUID> {

    protected TinyUUIDType(UUID value) {
        super(value);
    }

    @Override
    public Boolean isEmpty() {
        return getValue() == null;
    }

    public String getStringValue() {
        if (isEmpty()) {
            return "";
        }
        return getValue().toString();
    }
}
