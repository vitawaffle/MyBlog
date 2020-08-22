package by.vit.myblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User entity.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends MyEntity {

    /** Username. */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** Password. */
    @Column(name = "password", nullable = false)
    private String password;

    /** Is the user active. */
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    /** First name. */
    @Column(name = "first_name")
    private String firstName;

    /** Last name. */
    @Column(name = "last_name")
    private String lastName;

    /** Date of birth. */
    @Column(name = "dob")
    private Date dob;

    /** Bio. */
    @Column(name = "bio")
    private String bio;

    /** User's roles. */
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    private List<Role> roles;

}
