package com.github.bartoszpogoda.distmarketproducer.view;

import com.github.bartoszpogoda.distmarketproducer.service.OrderService;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ProductService productService;

    private final OrderService orderService;

    @Value("${spring.application.name}")
    private String appName;

    public DashboardController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("appName", this.appName);
        model.addAttribute("products", this.productService.getAll());
        model.addAttribute("orders", this.orderService.getAll());

        return "dashboard";
    }

}
