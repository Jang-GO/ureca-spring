package hello.phonemanager.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Owner {
    private Long id;
    private String username;
    private String password;
    private String name;
}
