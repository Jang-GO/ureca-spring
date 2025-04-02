package mycom.springphonemanager.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Owner {
    private int ownerId;
    private String name;
    private String phoneNumber;
    private UUID ownerUuid;
}
