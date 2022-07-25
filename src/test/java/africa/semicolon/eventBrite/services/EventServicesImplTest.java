package africa.semicolon.eventBrite.services;

import africa.semicolon.eventBrite.data.models.Event;
import africa.semicolon.eventBrite.data.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventServicesImplTest {
    @Autowired
    private EventServices eventServicesImpl;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void addNewPartyTest(){
        Event event = new Event();
        Event savedEvent = eventServicesImpl.saveEvent(event);
        assertEquals(1, eventRepository.count());
    }
}