package com.lmlnemesis.minesweeper.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class Size {

    @NotNull(message = "rows amount can not be null")
    private Integer rows;
    @NotNull(message = "columns amount can not be null")
    private Integer columns;

}
