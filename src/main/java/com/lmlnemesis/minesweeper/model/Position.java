package com.lmlnemesis.minesweeper.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Boolean mine = false;

    @Column(nullable = false)
    private Integer column;

    @Column(nullable = false)
    private Integer row;

    @Column(nullable = false)
    private Boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_active_positions")
    private Board board;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp modifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id) &&
                Objects.equals(column, position.column) &&
                Objects.equals(row, position.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, column, row);
    }
}
