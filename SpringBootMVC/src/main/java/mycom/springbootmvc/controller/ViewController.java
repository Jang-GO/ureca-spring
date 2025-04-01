package mycom.springbootmvc.controller;

import mycom.springbootmvc.dto.CarDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// DispatcherServlet 을 포함한 컨트롤러 단에서 작업 처리 후 data를 jsp
@Controller
public class ViewController {

    @GetMapping("/viewTest1")
    public String viewTest1(){
        return "viewTest1";
    }

    @GetMapping("/viewTest2")
    public String viewTest2(){
        return "sub/viewTest2";
    }

    @GetMapping("/viewTest3")
    public String viewTest3(Model model){
        model.addAttribute("seq", "12345");
        model.addAttribute("carDto", new CarDto("BMW",  "me", 12300));
        return "viewTest3";
    }

    @GetMapping("/viewTest4")
    public ModelAndView viewTest4(){
        // ModelAndView
        ModelAndView mav = new ModelAndView();
        mav.addObject("seq", "12345");
        mav.addObject("carDto", new CarDto("BMW",  "me", 12300));
        mav.setViewName("viewTest4");
        return mav;
    }

    @GetMapping("/redirect")
    public String redirect(){
        return "redirect:/viewTest1";
    }
}
