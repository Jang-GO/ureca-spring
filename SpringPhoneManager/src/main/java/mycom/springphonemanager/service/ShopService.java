package mycom.springphonemanager.service;

import lombok.RequiredArgsConstructor;
import mycom.springphonemanager.domain.Shop;
import mycom.springphonemanager.repository.mybatis.MBShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final MBShopRepository shopRepository;

    public List<Shop> findByOwnerId(int ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }
}
