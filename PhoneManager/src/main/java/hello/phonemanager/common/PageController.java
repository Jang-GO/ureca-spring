package hello.phonemanager.common;

import hello.phonemanager.domain.Owner;
import hello.phonemanager.service.SaleService;
import hello.phonemanager.service.ShopService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final ShopService shopService;
    private final SaleService saleService;

    public PageController(SaleService saleService, ShopService shopService) {
        this.saleService = saleService;
        this.shopService = shopService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Owner loginOwner = (Owner) session.getAttribute("loginOwner");

        if (loginOwner != null) {
            int totalShops = shopService.countShopsByOwnerId(loginOwner.getId());
            int monthlySales = saleService.countMonthlySalesByOwnerId(loginOwner.getId());
            long totalRevenue = saleService.calculateTotalRevenueByOwnerId(loginOwner.getId());

            model.addAttribute("totalShops", totalShops);
            model.addAttribute("monthlySales", monthlySales);
            model.addAttribute("totalRevenue", totalRevenue);
        }

        return "index";
    }

    @GetMapping("/owner/register")
    public String showRegisterForm() {
        return "owner/register";
    }
    // 로그인 폼
    @GetMapping("/owner/login")
    public String showLoginForm() {
        return "owner/login";
    }
}
