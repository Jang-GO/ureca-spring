package hello.phonemanager.service;

import hello.phonemanager.domain.Shop;
import hello.phonemanager.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopMapper) {
        this.shopRepository = shopMapper;
    }

    // 가맹점 등록
    public void register(Shop shop) {
        shopRepository.insertShop(shop);
    }

    // 특정 점주의 가맹점 목록 조회
    public List<Shop> findByOwnerId(Long ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }

    // 가맹점 하나 조회 (수정용)
    public Shop findById(Long id) {
        return shopRepository.findById(id);
    }

    // 가맹점 정보 수정
    public void update(Shop shop) {
        shopRepository.updateShop(shop);
    }

    // 가맹점 삭제
    public void delete(Long id) {
        shopRepository.deleteShop(id);
    }

    public int countShopsByOwnerId(Long ownerId) {
        return shopRepository.countByOwnerId(ownerId);
    }
}
