package com.lmlnemesis.minesweeper.dto.mapper;

import com.lmlnemesis.minesweeper.dto.PositionDto;
import com.lmlnemesis.minesweeper.dto.response.BoardResponse;
import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.Position;
import com.lmlnemesis.minesweeper.service.GameService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = GameService.class)
public interface BoardMapper {

    BoardResponse toResponse(Board board);

    PositionDto toDto(Position position);
}
