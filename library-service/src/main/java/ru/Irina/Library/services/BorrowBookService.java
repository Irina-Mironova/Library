package ru.Irina.Library.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.Irina.Library.entities.Book;
import ru.Irina.Library.entities.BorrowBook;
import ru.Irina.Library.entities.Reader;
import ru.Irina.Library.exceptions.BookMissingException;
import ru.Irina.Library.exceptions.ValidationException;
import ru.Irina.Library.repositories.BorrowBookRepository;
import ru.Irina.Library.exceptions.AmountNotEnoughException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class BorrowBookService {
    private final BorrowBookRepository borrowBookRepository;
    private final BookService bookService;
    private final ReaderService readerService;
    private final MessageSource messageSource;

    public BorrowBookService(BorrowBookRepository borrowBookRepository, BookService bookService,
                             ReaderService readerService, MessageSource messageSource) {
        this.borrowBookRepository = borrowBookRepository;
        this.bookService = bookService;
        this.readerService = readerService;
        this.messageSource = messageSource;
    }


    @Transactional
    public void takeBook(Long bookId, Long readerId) {
        if (bookId == null || readerId == null) {
            throw new ValidationException(List.of(messageSource.getMessage("valid.message.empty",
                    null, Locale.getDefault())));
        }

        Book book = bookService.findById(bookId);
        Reader reader = readerService.findById(readerId);

        if (book.getAmount() > 0) {
            BorrowBook borrowBook = new BorrowBook(book, reader);
            borrowBookRepository.save(borrowBook);
            bookService.decreaseAmount(book);
        } else {
            throw new AmountNotEnoughException(messageSource.getMessage("error.amount.book",
                    null, Locale.getDefault()));
        }
    }


    @Transactional
    public void giveBook(Long bookId, Long readerId) {
        Book book = bookService.findById(bookId);

        BorrowBook borrowBook = borrowBookRepository
                .findByBook_BookIdAndReader_ReaderIdAndDtReturnIsNull(bookId, readerId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BookMissingException(messageSource.getMessage("error.existent.id", null, Locale.getDefault())));

        borrowBook.setDtReturn(LocalDate.now());
        bookService.increaseAmount(book);
    }


}


