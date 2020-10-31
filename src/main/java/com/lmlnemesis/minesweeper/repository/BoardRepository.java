package com.lmlnemesis.minesweeper.repository;

import com.lmlnemesis.minesweeper.model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {

}
