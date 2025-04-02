package mycom.springphonemanager.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Shop {
    private int shopId;
    private String name;
    private String location;
    private LocalDate createdAt;
    private int ownerId;
}
