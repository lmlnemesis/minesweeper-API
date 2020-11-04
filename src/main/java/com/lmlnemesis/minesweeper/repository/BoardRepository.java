package com.lmlnemesis.minesweeper.repository;

import com.lmlnemesis.minesweeper.model.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {

    @Query(value = "SELECT b FROM Board b " +
            " LEFT JOIN Position ap ON ap.board.boardId = b.boardId AND ap.mine = false " +
            " WHERE  b.boardId = :boardId ")
    Optional<Board> findByIdWithoutMines(@Param("boardId") Integer boardId);

}
