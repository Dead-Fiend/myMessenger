package ageevcode.myMessenger.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@NoArgsConstructor
public class UserChat implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private UserChatId id;

    @MapsId("authorId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User author;

    @MapsId("interlocutorId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User interlocutor;

    @JsonView(Views.IdName.class)
    private boolean active;

    public UserChat(User author, User interlocutor) {
        this.author = author;
        this.interlocutor = interlocutor;
        this.id = new UserChatId(author.getId(), interlocutor.getId());
    }
}
