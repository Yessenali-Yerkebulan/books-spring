package com.example.domain.entities;

import com.example.domain.entities.AuthorEntity;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Enabled
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="books")
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long isbn;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    private AuthorEntity author;


}
