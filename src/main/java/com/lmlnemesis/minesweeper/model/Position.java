package com.lmlnemesis.minesweeper.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer positionId;

    @Column(nullable = false)
    private Integer colNbr;

    @Column(nullable = false)
    private Integer rowNbr;

    @Enumerated(EnumType.STRING)
    private Flag flag = Flag.NONE;

    @Column(nullable = false)
    private Boolean active = false;

    @Column(nullable = false)
    private Boolean mine = false;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Board.class)
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
        return Objects.equals(positionId, position.positionId) &&
                Objects.equals(colNbr, position.colNbr) &&
                Objects.equals(rowNbr, position.rowNbr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, colNbr, rowNbr);
    }
}
