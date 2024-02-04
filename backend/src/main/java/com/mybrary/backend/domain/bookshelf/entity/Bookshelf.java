package com.mybrary.backend.domain.bookshelf.entity;

import com.mybrary.backend.domain.mybrary.entity.Mybrary;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted <> true")
@SQLDelete(sql = "UPDATE bookshelf SET is_deleted = TRUE WHERE bookshelf_id = ?")
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookshelf_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mybrary_id")
    private Mybrary mybrary;

}
