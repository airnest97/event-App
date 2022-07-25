package africa.semicolon.eventBrite.dtos.requests;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
