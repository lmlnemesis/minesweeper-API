package com.lmlnemesis.minesweeper.dto;

import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.EFlag;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PositionDto {

    private Integer positionId;
    private Integer colNbr;
    private Integer rowNbr;
    private EFlag flag;
    private Boolean active;
    private Boolean mine;
    private Board board;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

}
