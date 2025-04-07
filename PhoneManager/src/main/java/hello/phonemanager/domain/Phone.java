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
}
