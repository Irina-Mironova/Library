package ru.Irina.Library.converters;

import org.springframework.stereotype.Component;
import ru.Irina.Library.entities.Book;
import ru.Irina.api.BookDto;

@Component
public class BookConverter implements LibraryConverter<Book, BookDto> {

    @Override
    public BookDto entityToDto(Book entity) {
        return new BookDto(entity.getBookId(),
                entity.getAuthor().getAuthorId(),
                entity.getAuthor().getAuthorName(),
                entity.getTitle(),
                entity.getYearPublish(),
                entity.getAmount()
        );
    }

    @Override
    public Book dtoToEntity(Book entity, BookDto dto) {
        if (entity == null) {
            entity = new Book();
        }

        entity.setTitle(dto.getTitle());
        entity.setYearPublish(dto.getYearPublish());
        entity.setAmount(dto.getAmount());

        return entity;
    }
}
