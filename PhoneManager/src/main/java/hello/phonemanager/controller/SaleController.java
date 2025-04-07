package hello.phonemanager.controller;

import hello.phonemanager.domain.Owner;
import hello.phonemanager.domain.dto.GroupedSaleDetail;
import hello.phonemanager.domain.dto.SaleDetail;
import hello.phonemanager.service.SaleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/sales/history")
    public String viewSalesByOwner(HttpSession session, Model model) {
        Owner owner = (Owner) session.getAttribute("loginOwner");
        if (owner == null) {
            return "redirect:/owner/login";
        }

        List<GroupedSaleDetail> groupedSales = saleService.getSalesGroupedByShop(owner.getId());
        int totalRevenue = saleService.getTotalRevenue(owner.getId());

        model.addAttribute("groupedSales", groupedSales);
        model.addAttribute("totalRevenue", totalRevenue);
        return "sale/sale-history";
    }
}
