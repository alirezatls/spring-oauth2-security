package com.sample.oauth2service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/api/public/user")
    public String getUserMessage() {
        return "hello user";
    }

    @GetMapping("/api/private/admin")
    public String getAdminMessage() {
        return "hello admin";
    }
}
