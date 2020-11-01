package com.lmlnemesis.minesweeper.util;

import com.lmlnemesis.minesweeper.model.Position;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PositionUtils {
    private PositionUtils() {
        //private constructor for utils
    }

    public static boolean existPosition(List<Position> positions, Integer row, Integer col) {
        return positions.stream()
                .anyMatch(position -> position.getRowNbr() == row && position.getColNbr() == col);
    }

    public static Integer getRandomPosition(Integer maxValue) {
        return ThreadLocalRandom.current().nextInt(0, maxValue);
    }
}
