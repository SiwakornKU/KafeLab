package ku.cs.kafe.controller;

import ku.cs.kafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "order-all";
    }
}
// 6410451423 Siwakorn Pasawang
