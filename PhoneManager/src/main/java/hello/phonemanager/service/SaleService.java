package hello.phonemanager.service;

import hello.phonemanager.domain.dto.GroupedSaleDetail;
import hello.phonemanager.domain.dto.SaleDetail;
import hello.phonemanager.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public List<SaleDetail> getSalesByOwner(Long ownerId) {
        return saleRepository.findSalesByOwnerId(ownerId);
    }

    public int getTotalRevenue(Long ownerId) {
        return saleRepository.calculateTotalRevenueByOwnerId(ownerId);
    }

    public List<GroupedSaleDetail> getSalesGroupedByShop(Long ownerId) {
        List<SaleDetail> sales = saleRepository.findSalesByOwnerId(ownerId);

        return sales.stream()
                .collect(Collectors.groupingBy(SaleDetail::getShopName))
                .entrySet().stream()
                .map(entry -> {
                    GroupedSaleDetail group = new GroupedSaleDetail();
                    group.setShopName(entry.getKey());
                    group.setSales(entry.getValue());
                    return group;
                })
                .collect(Collectors.toList());
    }

    public int countMonthlySalesByOwnerId(Long ownerId) {
        return saleRepository.countMonthlySalesByOwnerId(ownerId);
    }

    public long calculateTotalRevenueByOwnerId(Long ownerId) {
        return saleRepository.calculateTotalRevenueByOwnerId(ownerId);
    }
}
