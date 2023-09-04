package ku.cs.kafe.controller;

import ku.cs.kafe.model.AddCartRequest;
import ku.cs.kafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{menuId}")
    public String order(@PathVariable UUID menuId, @ModelAttribute AddCartRequest request, Model model){
        orderService.order(menuId, request);
        return "redirect:/menus";
    }
}
