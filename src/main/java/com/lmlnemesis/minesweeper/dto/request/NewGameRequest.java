package com.lmlnemesis.minesweeper.dto.request;

import com.lmlnemesis.minesweeper.dto.Size;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class NewGameRequest {

    @NotNull
    private Integer mineAmount;

    @NotNull
    private Size size;

}
