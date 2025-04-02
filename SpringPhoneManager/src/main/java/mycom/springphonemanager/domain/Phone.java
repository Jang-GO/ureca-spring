package mycom.springphonemanager.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Phone {
    private int phoneId;
    private String modelName;
    private String brand;
    private int price;
    private LocalDate createdAt;
}
