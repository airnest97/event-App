package africa.semicolon.eventBrite.data.repositories;

import africa.semicolon.eventBrite.data.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
