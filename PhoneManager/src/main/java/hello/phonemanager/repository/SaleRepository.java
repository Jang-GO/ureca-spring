package hello.phonemanager.repository;

import hello.phonemanager.domain.Sale;
import hello.phonemanager.domain.dto.SaleDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleRepository {
    void save(Sale sale);
    List<SaleDetail> findSalesByOwnerId(Long ownerId);
    int calculateTotalRevenueByOwnerId(Long ownerId);
    int countMonthlySalesByOwnerId(Long ownerId);
}
