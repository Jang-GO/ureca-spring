package mycom.springphonemanager.controller;

import lombok.RequiredArgsConstructor;
import mycom.springphonemanager.domain.Phone;
import mycom.springphonemanager.domain.Shop;
import mycom.springphonemanager.domain.ShopPhone;
import mycom.springphonemanager.dto.ShopPhoneDTO;
import mycom.springphonemanager.service.PhoneService;
import mycom.springphonemanager.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @GetMapping("/phone/{shopId}")
    public String phoneList(@PathVariable("shopId") int shopId,
                            @RequestParam("shopName") String shopName,
                            Model model) {
        List<ShopPhone> shopPhones = phoneService.findShopPhoneByShopId(shopId);
        List<ShopPhoneDTO> phoneList = new ArrayList<>();

        for(ShopPhone shopPhone: shopPhones) {
            Phone phone = phoneService.findPhoneByShopPhoneId(shopPhone.getPhoneId());
            if(phone != null) {
                phoneList.add(new ShopPhoneDTO(
                        phone.getModelName(),
                        phone.getBrand(),
                        phone.getPrice(),
                        shopPhone.getStock()
                ));
            }
        }

        model.addAttribute("shopName", shopName);
        model.addAttribute("phoneList", phoneList);

        return "phoneList";
    }
}
