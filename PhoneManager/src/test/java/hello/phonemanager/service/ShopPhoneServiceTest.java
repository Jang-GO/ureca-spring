package hello.phonemanager.service;

import hello.phonemanager.domain.Phone;
import hello.phonemanager.domain.Shop;
import hello.phonemanager.domain.dto.ShopPhoneDetail;
import hello.phonemanager.repository.PhoneRepository;
import hello.phonemanager.repository.ShopPhoneRepository;
import hello.phonemanager.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class ShopPhoneServiceTest {

    @Autowired
    private ShopPhoneService shopPhoneService;

    @Autowired
    private ShopPhoneRepository shopPhoneRepository;
    @Autowired
    private ShopRepository shopRepository;

    private Long shopId;
    private Long phoneId;
    @Autowired
    private PhoneRepository phoneRepository;

    @BeforeEach
    void setUp() {
        shopPhoneRepository.deleteAll(); // 자식 테이블 먼저 삭제
        shopRepository.deleteAll();      // 부모 테이블 삭제
        phoneRepository.deleteAll();

        // 테스트용 shop, phone 데이터 삽입
        Shop shop = new Shop();
        shop.setName("테스트 샵");
        shop.setAddress("충청북도 청주");
        shop.setOwnerId(1L);
        shopRepository.insertShop(shop);
        shopId = shop.getId(); // 여기서 DB에 저장된 ID 추출

        // Phone 삽입
        Phone phone = new Phone();
        phone.setName("갤럭시 S24");
        phone.setBrand("SAMSUNG");
        phone.setPrice(1000000);
        phoneRepository.insertPhone(phone);
        phoneId = phone.getId(); // 여기서 DB에 저장된 ID 추출
    }


    @Test
    @DisplayName("Shop에 새로운 휴대폰을 추가하면 저장된다.")
    void addPhoneToShop_whenNotExist_thenInsertNew() {
        // when
        shopPhoneService.addPhoneToShop(shopId, phoneId, 5);

        // then
        ShopPhoneDetail result = shopPhoneRepository.findByShopIdAndPhoneId(shopId, phoneId);
        assertNotNull(result);
        assertEquals(5, result.getStock());
    }

    @Test
    @DisplayName("Shop에 이미 존재하는 휴대폰이면 재고가 증가한다.")
    void addPhoneToShop_whenExists_thenStockUpdated() {
        // given
        shopPhoneService.addPhoneToShop(shopId, phoneId, 3);

        // when
        shopPhoneService.addPhoneToShop(shopId, phoneId, 2);

        // then
        ShopPhoneDetail result = shopPhoneRepository.findByShopIdAndPhoneId(shopId, phoneId);
        assertEquals(5, result.getStock()); // 3 + 2
    }
}
