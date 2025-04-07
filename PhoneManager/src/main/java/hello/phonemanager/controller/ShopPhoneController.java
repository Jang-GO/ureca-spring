package hello.phonemanager.controller;

import hello.phonemanager.domain.Phone;
import hello.phonemanager.domain.dto.ShopPhoneDetail;
import hello.phonemanager.service.ShopPhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopPhoneController {

    private final ShopPhoneService shopPhoneService;

    @GetMapping("/shop/phones")
    public String viewShopPhones(@RequestParam("shopId") Long shopId, Model model) {
        List<ShopPhoneDetail> phones = shopPhoneService.getPhoneDetailsByShopId(shopId);
        List<Phone> allPhones = shopPhoneService.getAllPhones(); // 전체 휴대폰 목록

        model.addAttribute("phones", phones);
        model.addAttribute("shopId", shopId);
        model.addAttribute("allPhones", allPhones);
        return "shop/phone-list";
    }

    @PostMapping("/shop/phones/add")
    public String addPhoneToShop(@RequestParam Long shopId, @RequestParam Long phoneId, @RequestParam int stock) {
        shopPhoneService.addPhoneToShop(shopId, phoneId, stock);
        return "redirect:/shop/phones?shopId=" + shopId;
    }

    @PostMapping("/shop/phones/sell")
    public String sellPhoneFromShop(@RequestParam Long shopId, @RequestParam Long phoneId, @RequestParam int quantity) {
        shopPhoneService.sellPhone(shopId, phoneId, quantity);
        return "redirect:/shop/phones?shopId=" + shopId;
    }

    @PostMapping("/shop/phones/delete")
    public String deletePhoneFromShop(@RequestParam Long shopId, @RequestParam Long phoneId) {
        shopPhoneService.deletePhoneFromShop(shopId, phoneId);
        return "redirect:/shop/phones?shopId=" + shopId;
    }
}
