package ru.Irina.Library.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.Irina.Library.converters.LibraryConverter;
import ru.Irina.Library.entities.Author;
import ru.Irina.Library.repositories.AuthorRepository;
import ru.Irina.api.AuthorDto;

import java.util.List;

@Service
public class AuthorService extends LibraryService<Author, Long, AuthorDto> {

    @Value("${pagination.page-size}")
    private int PAGE_SIZE;

    public AuthorService(JpaRepository<Author, Long> repository, LibraryConverter<Author,
            AuthorDto> converter, MessageSource messageSource) {
        super(repository, converter, messageSource);
    }

    public Page<Author> findByNamePage(String name, int page) {
        return ((AuthorRepository) repository).findByNamePage(name, PageRequest.of(page, PAGE_SIZE));
    }

    public List<Author> findByName(String name) {
        return ((AuthorRepository) repository).findByName(name);
    }

    public Author update(AuthorDto authorDto) {
        return super.update(authorDto, authorDto.getAuthorId());
    }

}

