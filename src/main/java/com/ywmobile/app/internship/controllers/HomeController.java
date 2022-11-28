package com.ywmobile.app.internship.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String welcome()
    {
        return "<center><h1>Hello World!</h1></center>";
    }
}
