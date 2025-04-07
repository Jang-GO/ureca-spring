package hello.phonemanager.repository;

import hello.phonemanager.domain.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopRepository {
    void insertShop(Shop shop);
    List<Shop> findByOwnerId(Long ownerId);
    Shop findById(Long id);
    void updateShop(Shop shop);
    void deleteShop(Long id);
    int countByOwnerId(Long ownerId);
}
