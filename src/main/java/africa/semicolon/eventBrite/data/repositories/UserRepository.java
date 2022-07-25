package africa.semicolon.eventBrite.data.repositories;

import africa.semicolon.eventBrite.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository  extends MongoRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
