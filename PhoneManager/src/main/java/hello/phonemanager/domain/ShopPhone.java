package hello.phonemanager.domain;

import lombok.Data;

@Data
public class ShopPhone {
    private Long id;
    private Long shopId;
    private Long phoneId;
    private Integer stock;
}
