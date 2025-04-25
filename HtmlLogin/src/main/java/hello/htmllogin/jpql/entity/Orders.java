package hello.htmllogin.jpql.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
//  @ManyToOne(fetch = FetchType.LAZY)
//  @ToString.Exclude
    private Customer customer;

    @ManyToOne
//  @ManyToOne(fetch = FetchType.LAZY)
//  @ToString.Exclude
    private Product product;


    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "order_date")
    private LocalDate orderDate;
}

