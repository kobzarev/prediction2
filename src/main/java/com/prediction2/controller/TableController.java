package com.prediction2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {
    @GetMapping("/table")
    public String drawTable() {
        return "table";
    }
}
