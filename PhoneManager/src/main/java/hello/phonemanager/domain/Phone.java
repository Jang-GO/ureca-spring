package hello.phonemanager.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Phone {
    private Long id;
    private String name;
    private String brand;
    private Integer price;
    private LocalDate releaseDate;

    // 기본 생성자 필수
    public Phone() {}

    // 또는 생성자 주입 방식이라면, 모든 파라미터 타입이 정확히 맞아야 함
    public Phone(Long id, String name, String brand, Integer price, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.releaseDate = releaseDate;
    }
}
