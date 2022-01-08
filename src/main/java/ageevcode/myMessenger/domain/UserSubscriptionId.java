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
public class UserSubscriptionId implements Serializable {
    @JsonView(Views.Id.class)
    private Long channelId;
    @JsonView(Views.Id.class)
    private Long subscriberId;
}
