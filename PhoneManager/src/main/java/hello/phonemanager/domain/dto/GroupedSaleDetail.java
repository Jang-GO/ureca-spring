package hello.phonemanager.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupedSaleDetail {
    private String shopName;
    private List<SaleDetail> sales;

    public int getTotalRevenue() {
        return sales.stream()
                .mapToInt(s -> s.getSalePrice() * s.getQuantity())
                .sum();
    }
}

