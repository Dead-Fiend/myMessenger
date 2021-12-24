package ageevcode.myMessenger.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(of = { "id" })
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String username;
    private String password;
    @JsonView(Views.WithoutPassword.class)
    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @JsonView(Views.WithoutPassword.class)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonView(Views.WithoutPassword.class)
    private LocalDateTime lastVisit;
    @Column(updatable = false)
    @JsonView(Views.WithoutPassword.class)
    private LocalDateTime createdAt;
    @JsonView(Views.WithoutPassword.class)
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonView(Views.WithoutPassword.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<UserDetails> subscriptions;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonView(Views.WithoutPassword.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<UserDetails> subscribers;

}
