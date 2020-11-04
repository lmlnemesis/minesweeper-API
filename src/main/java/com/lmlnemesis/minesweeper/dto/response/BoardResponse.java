package com.lmlnemesis.minesweeper.dto.response;

import com.lmlnemesis.minesweeper.dto.PositionDto;
import com.lmlnemesis.minesweeper.model.EBoardStatus;
import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardResponse {

    private Integer boardId;
    private Integer columns;
    private Integer rows;
    private EBoardStatus status;
    private List<PositionDto> activePositions = new ArrayList<>();
    private Timestamp createdAt;
    private Timestamp modifiedAt;
}
