package africa.semicolon.eventBrite.data.repositories;

import africa.semicolon.eventBrite.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }
}