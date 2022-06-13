package com.foo.auth.controller;

import com.foo.auth.entity.Product;
import com.foo.auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author foo
 * @since 2022-06-01
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MainPageController {

    private final ProductService productService;

    @GetMapping("/main")
    public String main(Authentication a, Model model) {
        log.info("访问到了 controller");
        model.addAttribute("username", a.getName());
        List<Product> list = productService.list();
        System.out.println("list = " + list);
        model.addAttribute("products", list);
        return "main.html";
    }

}
