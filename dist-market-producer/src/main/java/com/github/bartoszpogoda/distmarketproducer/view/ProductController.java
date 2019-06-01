package com.github.bartoszpogoda.distmarketproducer.view;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.exception.ProductNotFoundException;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/form")
    public String getProductForm(Model model, Long productId) {
        Product product = productId != null ? this.productService.getById(productId).orElse(emptyProduct()) : emptyProduct();
        model.addAttribute("product", product);

        return "productForm";
    }

    @PostMapping("/form")
    public String submitProductForm(Model model, @ModelAttribute Product product) {
        this.productService.saveOrUpdate(product);

        return "redirect:/dashboard";
    }

    @GetMapping("/remove")
    public String removeProduct(Long productId) {
        this.productService.unregister(productId);

        return "redirect:/dashboard";
    }


    private Product emptyProduct() {
        return Product.builder().id(null).build();
    }
}
