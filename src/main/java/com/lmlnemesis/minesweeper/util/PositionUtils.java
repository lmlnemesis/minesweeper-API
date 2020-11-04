package com.lmlnemesis.minesweeper.util;

import com.lmlnemesis.minesweeper.model.Board;
import com.lmlnemesis.minesweeper.model.Position;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PositionUtils {
    private PositionUtils() {
        //private constructor for utils
    }

    public static boolean existPosition(List<Position> positions, Integer row, Integer col) {
        return positions.stream()
                .anyMatch(position -> position.getRowNbr().equals(row) && position.getColNbr().equals(col));
    }

    public static Integer getRandomPosition(Integer maxValue) {
        return ThreadLocalRandom.current().nextInt(0, maxValue);
    }

    public static boolean isMine(Integer row, Integer col, List<Position> minePositions) {
        return minePositions.stream()
                .anyMatch(position ->
                        position.getColNbr().equals(col) &&
                                position.getRowNbr().equals(row)
                );
    }

    public static boolean isOutOfBoard (Board board, Integer row, Integer col) {
        return row >board.getRows() || col > board.getColumns();
    }
}
