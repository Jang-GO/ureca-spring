package mycom.springphonemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShopPhoneDTO {
    private String modelName;
    private String brand;
    private int price;
    private int stock;
}
