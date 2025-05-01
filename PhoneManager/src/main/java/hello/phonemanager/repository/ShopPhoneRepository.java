package hello.phonemanager.repository;

import hello.phonemanager.domain.Phone;
import hello.phonemanager.domain.dto.ShopPhoneDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopPhoneRepository {
    List<ShopPhoneDetail> findPhoneDetailsByShopId(@Param("shopId") Long shopId);
    void insertPhoneToShop(@Param("shopId") Long shopId, @Param("phoneId") Long phoneId, @Param("stock") int stock);
    void decreaseStock(@Param("shopId") Long shopId, @Param("phoneId") Long phoneId, @Param("quantity") int quantity);
    void removePhoneFromShop(@Param("shopId") Long shopId, @Param("phoneId") Long phoneId);
    List<Phone> findAll();

    ShopPhoneDetail findByShopIdAndPhoneId(@Param("shopId") Long shopId, @Param("phoneId") Long phoneId);

    void updateStock(@Param("shopId") Long shopId, @Param("phoneId") Long phoneId, @Param("stock") int additionalStock);

    // 테스트용 메서드
    void deleteAll();

}
