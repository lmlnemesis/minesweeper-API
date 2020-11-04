package com.lmlnemesis.minesweeper.service;

import com.lmlnemesis.minesweeper.dto.SizeDto;
import com.lmlnemesis.minesweeper.dto.mapper.BoardMapper;
import com.lmlnemesis.minesweeper.dto.response.BoardResponse;
import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.Position;
import com.lmlnemesis.minesweeper.repository.BoardRepository;
import com.lmlnemesis.minesweeper.util.PositionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class GameService {

    private BoardRepository boardRepository;

    private BoardMapper boardMapper;

    @Autowired
    public GameService(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public Integer createNewGame(Integer mineAmount, SizeDto boardSizeDto) {
        int maxMineAmount = boardSizeDto.getColumns() * boardSizeDto.getRows();

        this.validateAmountOfMines(mineAmount, maxMineAmount);

        Board newBoard = new Board();
        newBoard.setColumns(boardSizeDto.getColumns());
        newBoard.setRows(boardSizeDto.getRows());

        for (int i = 1; i <= mineAmount; i++) {
            Position minePosition = new Position();

            int randomRow = PositionUtils.getRandomPosition(boardSizeDto.getRows());
            int randomColumn = PositionUtils.getRandomPosition(boardSizeDto.getColumns());

            while (PositionUtils.existPosition(newBoard.getActivePositions(), randomRow, randomColumn)) {
                randomRow = PositionUtils.getRandomPosition(boardSizeDto.getRows());
                randomColumn = PositionUtils.getRandomPosition(boardSizeDto.getColumns());
            }

            minePosition.setRowNbr(randomRow);
            minePosition.setColNbr(randomColumn);

            newBoard.getActivePositions().add(minePosition);
        }

        newBoard = boardRepository.save(newBoard);
        return newBoard.getBoardId();
    }

    private void validateAmountOfMines(Integer mineAmount, int maxMineAmount) {
        if (mineAmount >= maxMineAmount) {
            throw new ResponseStatusException(BAD_REQUEST, "Amount of mines can not be grater or equals to slots in the board,");
        }
    }

    public BoardResponse getBoard(Integer boardId) {
        Optional<Board> boardOptional = boardRepository.findByIdWithoutMines(boardId);

        if (boardOptional.isPresent()) {
            return boardMapper.toResponse(boardOptional.get());
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Board Not Found");
        }
    }


}
