package nl.kristalsoftware.ddd.domain.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.ddd.domain.base.annotations.ValueObject;

@Slf4j
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ValueObject
public abstract class TinyType<T> {

    private final T value;

    @Override
    public final int hashCode() {
        return value.hashCode();
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName() + " [" + value + "]";
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) {
            log.info(getClass() + " " + obj.getClass());
            return false;
        }
        @SuppressWarnings("rawtypes")
        TinyType other = (TinyType) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    public abstract Boolean isEmpty();

}
