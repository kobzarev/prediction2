package com.prediction2.controller;

import com.prediction2.dto.Table;
import com.prediction2.service.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    private final TableService tableService;

    public DataController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/table/data")
    public Table drawTable() {
        return tableService.buildTable();
    }
}
