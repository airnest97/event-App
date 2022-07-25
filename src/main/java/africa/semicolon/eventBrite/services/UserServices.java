package africa.semicolon.eventBrite.services;

import africa.semicolon.eventBrite.dtos.requests.CreatePartyRequest;
import africa.semicolon.eventBrite.dtos.requests.LoginUserRequest;
import africa.semicolon.eventBrite.dtos.requests.RegisterUserRequest;
import africa.semicolon.eventBrite.dtos.responses.CreateEventResponse;
import africa.semicolon.eventBrite.dtos.responses.LoginUserResponse;
import africa.semicolon.eventBrite.dtos.responses.RegisterUserResponse;

public interface UserServices {

    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse loginUser(LoginUserRequest request);

    CreateEventResponse addParty(CreatePartyRequest request);
}
