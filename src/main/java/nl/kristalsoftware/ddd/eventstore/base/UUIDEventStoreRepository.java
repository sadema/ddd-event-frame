package nl.kristalsoftware.ddd.eventstore.base;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UUIDEventStoreRepository extends CrudRepository<UUIDBaseEventEntity, Long> {

    Iterable<UUIDBaseEventEntity<?>> findAllByReference(UUID value);

    Optional<UUIDBaseEventEntity<?>> findFirstByReference(UUID value);

}
