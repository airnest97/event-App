package africa.semicolon.eventBrite.utils;

import africa.semicolon.eventBrite.data.models.Event;
import africa.semicolon.eventBrite.data.models.User;
import africa.semicolon.eventBrite.dtos.requests.CreatePartyRequest;
import africa.semicolon.eventBrite.dtos.requests.RegisterUserRequest;
import africa.semicolon.eventBrite.dtos.responses.CreateEventResponse;
import africa.semicolon.eventBrite.dtos.responses.LoginUserResponse;
import africa.semicolon.eventBrite.dtos.responses.RegisterUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static void map(RegisterUserRequest request, User user) {
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
    }

    public static void map(User savedUser, RegisterUserResponse response) {
        response.setEmail(savedUser.getEmail());
        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy, hh:mm, a").format(savedUser.getDateRegistered()));
    }

    public static void map(CreatePartyRequest request, Event event) {
        event.setLocation(request.getPartyLocation());
        event.setName(request.getPartyName());
    }

    public static void map(User foundUser, Event savedEvent, CreateEventResponse response) {
        response.setPartyLocation(savedEvent.getLocation());
        response.setCreatedBy(foundUser.getFirstName());
        response.setPartyName(savedEvent.getName());
    }
}
