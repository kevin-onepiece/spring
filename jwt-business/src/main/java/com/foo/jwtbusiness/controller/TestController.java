package com.foo.jwtbusiness.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foo
 * @since 2022-06-05
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
