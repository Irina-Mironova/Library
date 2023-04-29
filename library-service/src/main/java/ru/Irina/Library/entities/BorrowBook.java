package ru.Irina.Library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books_readers")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @CreationTimestamp
    @Column(name = "dt_borrow")
    private LocalDate dtBorrow;

    @Column(name = "dt_return")
    private LocalDate dtReturn;

    public BorrowBook(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }
}
