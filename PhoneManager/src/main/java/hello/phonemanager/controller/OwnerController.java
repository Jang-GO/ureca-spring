package hello.phonemanager.controller;

import hello.phonemanager.domain.Owner;
import hello.phonemanager.service.OwnerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute Owner owner) {
        System.out.println(owner);
        ownerService.register(owner);
        return "redirect:/owner/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Owner loginOwner = ownerService.login(username, password);
        if (loginOwner == null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "owner/login";
        }
        session.setAttribute("loginOwner", loginOwner);
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
