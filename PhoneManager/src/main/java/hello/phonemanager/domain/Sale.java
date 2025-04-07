package hello.phonemanager.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sale {
    private Long id;
    private Long shopId;
    private Long phoneId;
    private Integer quantity;
    private Integer salePrice;
    private LocalDateTime saleDate;
}
