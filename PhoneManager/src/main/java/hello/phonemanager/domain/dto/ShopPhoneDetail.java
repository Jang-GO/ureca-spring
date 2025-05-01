package hello.phonemanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShopPhoneDetail {
    private Long phoneId;
    private String name;
    private String brand;
    private Integer price;
    private LocalDate releaseDate;
    private Integer stock;

    public ShopPhoneDetail(Long phoneId, Integer stock, String name, String brand, Integer price, LocalDate releaseDate) {
        this.phoneId = phoneId;
        this.stock = stock;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }
}
