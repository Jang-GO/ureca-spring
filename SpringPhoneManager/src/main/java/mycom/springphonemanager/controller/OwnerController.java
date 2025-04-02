package mycom.springphonemanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycom.springphonemanager.domain.Owner;
import mycom.springphonemanager.domain.Shop;
import mycom.springphonemanager.service.OwnerService;
import mycom.springphonemanager.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService ownerService;
    private final ShopService shopService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("uuid") String uuid, Model model){
        log.info("uuid = {}", uuid);
        Owner owner = ownerService.findByUUID(uuid);
        if(owner != null){
            List<Shop> shopList = shopService.findByOwnerId(owner.getOwnerId());
            model.addAttribute("owner", owner);
            model.addAttribute("shopList", shopList);
            return "shopList";
        }
        model.addAttribute("errorMessage", "로그인에 실패하였습니다. UUID를 확인해주세요.");
        return "login";
    }
}
