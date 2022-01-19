package ageevcode.myMessenger.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatId implements Serializable {
    @JsonView(Views.Id.class)
    private Long authorId;
    @JsonView(Views.Id.class)
    private Long interlocutorId;
}
