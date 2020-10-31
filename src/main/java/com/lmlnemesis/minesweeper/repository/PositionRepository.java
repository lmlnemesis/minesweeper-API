package com.lmlnemesis.minesweeper.repository;

import com.lmlnemesis.minesweeper.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {
}
