package ru.Irina.Library.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.Irina.Library.converters.LibraryConverter;
import ru.Irina.Library.entities.Reader;
import ru.Irina.Library.repositories.ReaderRepository;
import ru.Irina.api.ReaderDto;

import java.util.List;

@Service
public class ReaderService extends LibraryService<Reader, Long, ReaderDto> {

    @Value("${pagination.page-size}")
    private int PAGE_SIZE;

    public ReaderService(JpaRepository<Reader, Long> repository, LibraryConverter<Reader,
            ReaderDto> converter, MessageSource messageSource) {

        super(repository, converter, messageSource);
    }

    public Page<Reader> findByNamePage(String name, int page) {
        return ((ReaderRepository) repository).findByNamePage(name, PageRequest.of(page, PAGE_SIZE));
    }

    public List<Reader> findByName(String name) {
        return ((ReaderRepository) repository).findByName(name);
    }

    public Reader update(ReaderDto readerDto) {
        return super.update(readerDto, readerDto.getReaderId());
    }

}
