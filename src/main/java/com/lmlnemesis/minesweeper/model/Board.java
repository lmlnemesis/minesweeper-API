package com.lmlnemesis.minesweeper.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer columns;

    @Column(nullable = false)
    private Integer rows;

    @OneToMany(mappedBy = "board")
    @Cascade(value = CascadeType.ALL)
    private List<Position> activePositions = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp modifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
