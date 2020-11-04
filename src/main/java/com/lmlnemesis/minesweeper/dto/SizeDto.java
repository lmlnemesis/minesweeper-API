package com.lmlnemesis.minesweeper.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class SizeDto {

    @NotNull(message = "rows amount can not be null")
    @Min(value=1, message = "Row should be grater than 0")
    private Integer rows;

    @NotNull(message = "columns amount can not be null")
    @Min(value=1, message = "Row should be grater than 0")
    private Integer columns;

}
