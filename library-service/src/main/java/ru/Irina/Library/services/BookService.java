package ru.Irina.Library.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.Irina.Library.converters.LibraryConverter;
import ru.Irina.Library.entities.Book;
import ru.Irina.Library.repositories.BookRepository;
import ru.Irina.Library.repositories.specifications.BookSpecification;
import ru.Irina.api.BookDto;
import ru.Irina.api.BookFilter;

import javax.transaction.Transactional;

@Service
public class BookService extends LibraryService<Book, Long, BookDto> {
    private final AuthorService authorService;

    @Value("${pagination.page-size}")
    private int PAGE_SIZE;

    public BookService(JpaRepository<Book, Long> repository, LibraryConverter<Book, BookDto> converter,
                       MessageSource messageSource, AuthorService authorService) {

        super(repository, converter, messageSource);
        this.authorService = authorService;
    }


    public Page<Book> findByNameAndTitlePage(Specification<Book> specification, int page) {
        return ((BookRepository) repository).findAll(specification, PageRequest.of(page, PAGE_SIZE));

    }


    @Override
    @Transactional
    public Book add(BookDto dto) {
        Book entity = converter.dtoToEntity(null, dto);
        entity.setAuthor(authorService.findById(dto.getAuthorId()));

        return save(entity);
    }


    @Transactional
    public Book update(BookDto dto) {
        //  ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
        //   String greeting = resourceBundle.getString("validateq1.message.notBlank");
        Book entity = converter.dtoToEntity(findById(dto.getBookId()), dto);
        entity.setAuthor(authorService.findById(dto.getAuthorId()));

        return save(entity);
    }

    public Specification<Book> createSpecByFilters(BookFilter bookFilter) {
        Specification<Book> specification = Specification.where(null);

        if (bookFilter != null) {
            if (bookFilter.getAuthorName() != null) {
                specification = specification.and(BookSpecification.authorNameContains(bookFilter.getAuthorName()));
            }
            if (bookFilter.getTitle() != null) {
                specification = specification.and(BookSpecification.titleContains(bookFilter.getTitle()));
            }
        }

        return specification;
    }


    public int getAmount(Long id) {
        return findById(id).getAmount();
    }


    public void decreaseAmount(Book book) {
        book.setAmount(book.getAmount() - 1);
        save(book);
    }

    public void increaseAmount(Book book) {
        book.setAmount(book.getAmount() + 1);
        save(book);
    }
}

