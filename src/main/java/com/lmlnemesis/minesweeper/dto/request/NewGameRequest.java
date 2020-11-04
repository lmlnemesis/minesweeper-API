package com.lmlnemesis.minesweeper.dto.request;

import com.lmlnemesis.minesweeper.dto.SizeDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class NewGameRequest {

    @NotNull(message = "mineAmount amount can not be null")
    private Integer mineAmount;

    @NotNull
    @Valid
    private SizeDto size;

}
