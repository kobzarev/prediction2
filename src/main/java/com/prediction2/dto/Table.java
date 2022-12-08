package com.prediction2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Table {
    private List<UserDTO> users;
    private List<StageDTO> stages;
}
