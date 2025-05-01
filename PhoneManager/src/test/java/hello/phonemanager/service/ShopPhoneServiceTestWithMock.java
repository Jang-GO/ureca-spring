package hello.phonemanager.service;

import hello.phonemanager.domain.dto.ShopPhoneDetail;
import hello.phonemanager.repository.SaleRepository;
import hello.phonemanager.repository.ShopPhoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ShopPhoneServiceTestWithMock {
    @InjectMocks
    private ShopPhoneService shopPhoneService;

    @Mock
    private ShopPhoneRepository shopPhoneRepository;

    @Mock
    private SaleRepository saleRepository;

    @Test
    @DisplayName("휴대폰이 이미 가맹점에 존재하면, 재고가 증가한다.")
    void addPhoneToShopExist() {
        when(shopPhoneRepository.findByShopIdAndPhoneId(1L, 1L))
                .thenReturn(new ShopPhoneDetail(1L,3,"갤럭시 S7", "삼성",10000, LocalDate.of(2025,5,1)));

        shopPhoneService.addPhoneToShop(1L, 1L, 3);

        // 실제로 updateStock 메서드가 호촐되었는지 검증
        verify(shopPhoneRepository).updateStock(1L, 1L, 3);
    }

    @Test
    @DisplayName("휴대폰이 가맹점에 존재하지 않으면 새로 추가된다.")
    void addPhoneToShopWithNoExist(){
        when(shopPhoneRepository.findByShopIdAndPhoneId(1L, 1L))
                .thenReturn(null);

        // when
        shopPhoneService.addPhoneToShop(1L, 1L, 3);

        // then
        verify(shopPhoneRepository).insertPhoneToShop(1L, 1L, 3);
    }
}