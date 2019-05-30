package com.github.bartoszpogoda.distmarketproducer.view;

import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ProductService productService;

    @Value("${spring.application.name}")
    private String appName;

    public DashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("appName", this.appName);
        model.addAttribute("products", this.productService.getAll());

        return "dashboard";
    }

}
