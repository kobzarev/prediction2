package com.prediction2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StageDTO {
    private String Stage;
    private List<MatchDTO> matches;
}
