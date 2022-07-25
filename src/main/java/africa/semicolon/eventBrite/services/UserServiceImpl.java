package africa.semicolon.eventBrite.services;

import africa.semicolon.eventBrite.data.models.Event;
import africa.semicolon.eventBrite.data.models.User;
import africa.semicolon.eventBrite.data.repositories.UserRepository;
import africa.semicolon.eventBrite.dtos.requests.CreatePartyRequest;
import africa.semicolon.eventBrite.dtos.requests.LoginUserRequest;
import africa.semicolon.eventBrite.dtos.requests.RegisterUserRequest;
import africa.semicolon.eventBrite.dtos.responses.CreateEventResponse;
import africa.semicolon.eventBrite.dtos.responses.LoginUserResponse;
import africa.semicolon.eventBrite.dtos.responses.RegisterUserResponse;
import africa.semicolon.eventBrite.exceptions.DuplicateEmailException;
import africa.semicolon.eventBrite.exceptions.InvalidDetailsException;
import africa.semicolon.eventBrite.exceptions.UserDoesNotExistException;
import africa.semicolon.eventBrite.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserServices {

    private UserRepository userRepository;
    private EventServices eventServices;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EventServices eventServices){
        this.userRepository = userRepository;
        this.eventServices = eventServices;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) throw new DuplicateEmailException(request.getEmail() + " already exists");

//        Optional<User> userOptional = userRepository.findByEmail(request.getEmail()).getEmail();
//        if (userOptional.isPresent()) throw new DuplicateEmailException(request.getEmail() + " already exists")

        User user = new User();
        Mapper.map(request, user);
        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        Mapper.map(savedUser, response);
        return response;
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        LoginUserResponse response = new LoginUserResponse();
        if (user.getPassword().equals(request.getPassword())) {
            response.setMessage("Logged in successfully");
            return response;
        }
        throw new InvalidDetailsException("Invalid login details");
    }

    @Override
    public CreateEventResponse addParty(CreatePartyRequest request) {
        Optional<User> optionalUser = userRepository.findUserByEmail(request.getEmail());
        if (optionalUser.isEmpty()) throw new UserDoesNotExistException(request.getEmail() + " does not exist");
        User foundUser = optionalUser.get();
        Event event = new Event();
        Mapper.map(request, event);
        Event savedEvent = eventServices.saveEvent(event);
        foundUser.getEvents().add(savedEvent);
        userRepository.save(foundUser);

        CreateEventResponse response = new CreateEventResponse();
        Mapper.map(foundUser, savedEvent, response);

        return response;
    }
}
