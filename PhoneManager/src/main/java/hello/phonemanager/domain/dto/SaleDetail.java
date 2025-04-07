package hello.phonemanager.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleDetail {
    private String shopName;
    private String phoneName;
    private int quantity;
    private int salePrice;
    private LocalDateTime saleDate;

}

