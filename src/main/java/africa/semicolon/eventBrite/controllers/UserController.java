package africa.semicolon.eventBrite.controllers;

import africa.semicolon.eventBrite.dtos.requests.CreatePartyRequest;
import africa.semicolon.eventBrite.dtos.requests.LoginUserRequest;
import africa.semicolon.eventBrite.dtos.requests.RegisterUserRequest;
import africa.semicolon.eventBrite.dtos.responses.ApiResponse;
import africa.semicolon.eventBrite.exceptions.DuplicateEmailException;
import africa.semicolon.eventBrite.exceptions.UserDoesNotExistException;
import africa.semicolon.eventBrite.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserServices userServices;

    public UserController(@Autowired UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            var serviceResponse = userServices.registerUser(registerUserRequest);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (DuplicateEmailException ex){
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            var serviceResponse = userServices.loginUser(loginUserRequest);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex){
            ApiResponse response = new ApiResponse(false, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/party")
    public ResponseEntity<?> createEvent(@RequestBody CreatePartyRequest createPartyRequest){
        try {
            var serviceResponse = userServices.addParty(createPartyRequest);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (UserDoesNotExistException ex){
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
