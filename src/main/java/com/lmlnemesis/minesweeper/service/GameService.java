package com.lmlnemesis.minesweeper.service;

import com.lmlnemesis.minesweeper.dto.SizeDto;
import com.lmlnemesis.minesweeper.dto.mapper.BoardMapper;
import com.lmlnemesis.minesweeper.dto.request.PlayRequest;
import com.lmlnemesis.minesweeper.dto.response.BoardResponse;
import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.EBoardStatus;
import com.lmlnemesis.minesweeper.model.Position;
import com.lmlnemesis.minesweeper.repository.BoardRepository;
import com.lmlnemesis.minesweeper.repository.PositionRepository;
import com.lmlnemesis.minesweeper.util.PositionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class GameService {

    private BoardRepository boardRepository;

    private PositionRepository positionRepository;

    private BoardMapper boardMapper;

    @Autowired
    public GameService(BoardRepository boardRepository,PositionRepository positionRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.positionRepository = positionRepository;
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
            throw new ResponseStatusException(BAD_REQUEST, "Amount of mines can not be grater or equals to slots in the board.");
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

    public BoardResponse play(Integer boardId, PlayRequest playRequest) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();

            validatePlay(playRequest, board);

            List<Position> activePositions = board.getActivePositions();

           Optional<Position> existingPosition = activePositions.stream()
                    .filter(position ->
                            position.getColNbr().equals(playRequest.getColPos()) &&
                            position.getRowNbr().equals(playRequest.getRowPos())
                    ).findFirst();

           if (existingPosition.isPresent()) {
              Position position = existingPosition.get();
              if (position.getMine()) {
                  position.setActive(true);
                  board.setStatus(EBoardStatus.YOU_LOST);
              } else {
                  throw new ResponseStatusException(BAD_REQUEST, "You already did this move.");
              }
           } else {
               List<Position> minePositions =  activePositions.stream()
                       .filter(Position::getMine).collect(Collectors.toList());

               List<Position> revealPositions = revealAdjacent(minePositions, board, playRequest);
               board.getActivePositions().addAll(revealPositions);
               
               //TODO:REVIEW GAME Status
               boardRepository.save(board);
           }

           return boardMapper.toResponse(boardRepository.findByIdWithoutMines(boardId).get());
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Board Not Found");
        }
    }

    private void validateGame() {

    }

    private void validatePlay(PlayRequest playRequest, Board board) {
        if(board.getColumns() < playRequest.getColPos() || board.getRows() < playRequest.getRowPos() ) {
            throw new ResponseStatusException(BAD_REQUEST, "Move out of the board");
        } else if (EBoardStatus.FINISHED.equals(board.getStatus())) {
            throw new ResponseStatusException(BAD_REQUEST, "The Game already finish.");
        }
    }

    private List<Position> revealAdjacent(List<Position> minePositions, Board board, PlayRequest playRequest) {
        List<Position> revealPosition = new ArrayList<>();
        for (int colMove =  playRequest.getColPos() -1; colMove <= playRequest.getRowPos() + 1; colMove++ ) {
            for (int rowMove =  playRequest.getColPos() -1; rowMove <= playRequest.getRowPos() + 1; rowMove++ ) {
                boolean isNotMine = !PositionUtils.isMine(rowMove,colMove,minePositions);
                boolean isNotOutOfBoard = !PositionUtils.isOutOfBoard(board,rowMove,colMove);

                if (colMove > 0 && isNotMine && isNotOutOfBoard) {
                    Position newActivePosition = new Position();
                    newActivePosition.setActive(true);
                    newActivePosition.setColNbr(playRequest.getColPos());
                    newActivePosition.setRowNbr(playRequest.getRowPos());
                    newActivePosition.setBoard(board);
                    revealPosition.add(newActivePosition);
                }
            }
        }

        return revealPosition;
    }




}
