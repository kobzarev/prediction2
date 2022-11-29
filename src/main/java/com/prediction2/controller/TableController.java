package com.prediction2.controller;

import com.prediction2.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/table")
    public String drawTable(Model model) {
        tableService.buildTable();
        return "table";
    }
}
