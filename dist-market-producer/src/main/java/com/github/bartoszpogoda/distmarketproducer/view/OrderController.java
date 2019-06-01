package com.github.bartoszpogoda.distmarketproducer.view;

import com.github.bartoszpogoda.distmarketproducer.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("viewOrderController")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/remove")
    public String removeOrder(Long orderId) {
        this.orderService.removeOrder(orderId);

        return "redirect:/dashboard";
    }

}
