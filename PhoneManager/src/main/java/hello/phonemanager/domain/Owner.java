package hello.phonemanager.domain;

import lombok.Data;

@Data
public class Owner {
    private Long id;
    private String username;
    private String password;
    private String name;
}
