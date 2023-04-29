package ru.Irina.Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Irina.Library.entities.BorrowBook;

import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {

    List<BorrowBook> findByBook_BookIdAndReader_ReaderIdAndDtReturnIsNull(Long bookId, Long readerId);
}
