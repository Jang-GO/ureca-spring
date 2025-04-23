package hello.htmllogin.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter @Setter
@ToString
public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();
}
