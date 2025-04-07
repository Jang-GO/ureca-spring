package hello.phonemanager.controller;

import hello.phonemanager.domain.Owner;
import hello.phonemanager.domain.Shop;
import hello.phonemanager.service.ShopService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/new")
    public String registerForm() {
        return "shop/registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Shop shop, HttpSession session) {
        Owner loginOwner = (Owner) session.getAttribute("loginOwner");
        shop.setOwnerId(loginOwner.getId());
        shopService.register(shop);
        return "redirect:/shop/list";
    }

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        Owner loginOwner = (Owner) session.getAttribute("loginOwner");
        List<Shop> shopList = shopService.findByOwnerId(loginOwner.getId());
        model.addAttribute("shopList", shopList);
        return "shop/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "shop/editForm";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Shop shop) {
        shopService.update(shop);
        return "redirect:/shop/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        shopService.delete(id);
        return "redirect:/shop/list";
    }
}
