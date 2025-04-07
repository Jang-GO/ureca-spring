package hello.phonemanager.domain.dto;

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
}
