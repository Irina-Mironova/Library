package ru.Irina.Library.converters;

import org.springframework.stereotype.Component;
import ru.Irina.Library.entities.Author;
import ru.Irina.api.AuthorDto;

@Component
public class AuthorConverter implements LibraryConverter<Author, AuthorDto> {

    @Override
    public AuthorDto entityToDto(Author entity) {
        return new AuthorDto(entity.getAuthorId(), entity.getAuthorName(), entity.getDescription());
    }

    @Override
    public Author dtoToEntity(Author entity, AuthorDto dto) {
        if (entity == null) {
            entity = new Author();
        }

        entity.setAuthorName(dto.getAuthorName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

}
