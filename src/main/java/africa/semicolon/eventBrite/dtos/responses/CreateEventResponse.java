package africa.semicolon.eventBrite.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEventResponse {
    private String partyName;
    private String partyLocation;
    private String createdBy;
}
