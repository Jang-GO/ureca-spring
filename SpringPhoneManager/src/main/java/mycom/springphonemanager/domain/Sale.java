package mycom.springphonemanager.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sale {
    private int sale_id;
    private int quantity;
    private int totalPrice;
    private LocalDateTime saleDate;
    private int customerId;
    private int shopId;
    private int phoneId;
}
