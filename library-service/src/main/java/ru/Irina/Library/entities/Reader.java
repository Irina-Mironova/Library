package ru.Irina.Library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "readers")
public class Reader {
    @Id
    @Column(name = "reader_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readerId;

    private String readerName;

    private LocalDate dtBirth;

    private String address;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reader")
    private List<BorrowBook> borrowBooks;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

