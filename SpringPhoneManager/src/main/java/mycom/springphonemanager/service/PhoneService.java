package mycom.springphonemanager.service;

import lombok.RequiredArgsConstructor;
import mycom.springphonemanager.domain.Phone;
import mycom.springphonemanager.domain.ShopPhone;
import mycom.springphonemanager.repository.mybatis.MBPhoneRepository;
import mycom.springphonemanager.repository.mybatis.MBShopPhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final MBShopPhoneRepository shopPhoneRepository;
    private final MBPhoneRepository phoneRepository;

    public List<ShopPhone> findShopPhoneByShopId(int shopId) {
        return shopPhoneRepository.findPhonesByShopId(shopId);
    }

    public Phone findPhoneByShopPhoneId(int phoneId) {
        return phoneRepository.findById(phoneId);
    }
}
