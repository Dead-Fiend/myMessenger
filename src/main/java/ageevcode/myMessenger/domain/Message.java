package ageevcode.myMessenger.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String text;
    @Column(updatable = false)
    @JsonView(Views.FullMessage.class)
    private LocalDateTime createdAt;
    @JsonView(Views.FullMessage.class)
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullMessage.class)
    private UserDetails author;
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    @JsonView(Views.FullMessage.class)
    private List<Comment> comments;

}
