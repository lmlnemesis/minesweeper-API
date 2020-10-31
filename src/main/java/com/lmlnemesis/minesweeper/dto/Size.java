package com.lmlnemesis.minesweeper.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class Size {

    @NotNull
    private Integer rows;
    @NotNull
    private Integer columns;

}
