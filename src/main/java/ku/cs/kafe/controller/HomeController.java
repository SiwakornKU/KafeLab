package ku.cs.kafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // return .html
public class HomeController {
    @RequestMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("greeting","Sawaddee");

        return "homepage";
    }
}
// 6410451423 Siwakorn Pasawang