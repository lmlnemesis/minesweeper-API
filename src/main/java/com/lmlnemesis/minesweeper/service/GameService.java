package com.lmlnemesis.minesweeper.service;

import com.lmlnemesis.minesweeper.dto.Size;
import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.Position;
import com.lmlnemesis.minesweeper.repository.BoardRepository;
import com.lmlnemesis.minesweeper.util.PositionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService {

    private BoardRepository boardRepository;

    @Autowired
    public GameService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Integer createNewGame(Integer mineAmount, Size boardSize) {

        Board newBoard =  new Board();
        newBoard.setColumns(boardSize.getColumns());
        newBoard.setRows(boardSize.getRows());

        for(int i = 1; i <= mineAmount; i++) {
            Position minePosition = new Position();

            int randomRow = PositionUtils.getRandomPosition(boardSize.getRows());
            int randomColumn =PositionUtils.getRandomPosition(boardSize.getColumns());

            while(PositionUtils.existPosition(newBoard.getActivePositions(), randomRow, randomColumn)) {
                randomRow = PositionUtils.getRandomPosition(boardSize.getRows());
                randomColumn =PositionUtils.getRandomPosition(boardSize.getColumns());
            }

            minePosition.setRowNbr(randomRow);
            minePosition.setColNbr(randomColumn);

            newBoard.getActivePositions().add(minePosition);
        }

        newBoard = boardRepository.save(newBoard);
        return newBoard.getBoardId();
    }

}
