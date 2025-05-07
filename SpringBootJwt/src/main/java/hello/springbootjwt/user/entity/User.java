package hello.springbootjwt.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    //    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<UserRole> userRoles = new ArrayList<>();
}
