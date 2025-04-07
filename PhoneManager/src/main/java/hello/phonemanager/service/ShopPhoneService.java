package hello.phonemanager.service;

import hello.phonemanager.domain.Phone;
import hello.phonemanager.domain.Sale;
import hello.phonemanager.domain.ShopPhone;
import hello.phonemanager.domain.dto.ShopPhoneDetail;
import hello.phonemanager.repository.SaleRepository;
import hello.phonemanager.repository.ShopPhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopPhoneService {

    private final ShopPhoneRepository shopPhoneRepository;
    private final SaleRepository saleRepository;

    public List<ShopPhoneDetail> getPhoneDetailsByShopId(Long shopId) {
        return shopPhoneRepository.findPhoneDetailsByShopId(shopId);
    }

    public void addPhoneToShop(Long shopId, Long phoneId, int stock) {
        ShopPhoneDetail existing = shopPhoneRepository.findByShopIdAndPhoneId(shopId, phoneId);

        if (existing != null) {
            // 재고만 증가
            shopPhoneRepository.updateStock(shopId, phoneId, stock);
        } else {
            // 새로 등록
            shopPhoneRepository.insertPhoneToShop(shopId, phoneId, stock);
        }
    }


    @Transactional
    public void sellPhone(Long shopId, Long phoneId, int quantity) {
        // 1. 현재 재고 확인
        ShopPhoneDetail shopPhone = shopPhoneRepository.findByShopIdAndPhoneId(shopId, phoneId);
        if (shopPhone == null || shopPhone.getStock() < quantity) {
            throw new IllegalArgumentException("재고 부족 또는 해당 휴대폰 없음");
        }

        shopPhoneRepository.decreaseStock(shopId,phoneId,quantity);

        ShopPhoneDetail phone = shopPhoneRepository.findByShopIdAndPhoneId(shopId,phoneId);
        int salePrice = phone.getPrice(); // 또는 shopPhone.getPrice() 등

        Sale sale = new Sale();
        sale.setShopId(shopId);
        sale.setPhoneId(phoneId);
        sale.setQuantity(quantity);
        sale.setSalePrice(salePrice*quantity);
        sale.setSaleDate(LocalDateTime.now());

        saleRepository.save(sale);
    }


    public void deletePhoneFromShop(Long shopId, Long phoneId) {
        shopPhoneRepository.removePhoneFromShop(shopId, phoneId);
    }

    public List<Phone> getAllPhones() {
        return shopPhoneRepository.findAll();
    }
}
