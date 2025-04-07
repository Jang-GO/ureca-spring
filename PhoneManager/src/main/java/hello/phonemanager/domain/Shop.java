package hello.phonemanager.domain;

import lombok.Data;

@Data
public class Shop {
    private Long id;
    private Long ownerId;
    private String name;
    private String address;
}
