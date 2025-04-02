package mycom.springphonemanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDTO {
    private String customerName;
    private String modelName;
    private String customerPhoneNumber;
    private int quantity;
    private int totalPrice;
    private LocalDateTime saleDate;

    public SaleDTO(String customerName, String customerPhoneNumber, String modelName, int quantity, LocalDateTime saleDate, int totalPrice) {
        this.customerName = customerName;
        this.modelName = modelName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
    }

    public SaleDTO(String customerName, String modelName, int quantity, int totalPrice, LocalDateTime saleDate) {
        this.customerName = customerName;
        this.modelName = modelName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }
}
