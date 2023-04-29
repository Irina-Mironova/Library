package ru.Irina.Library.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import ru.Irina.Library.converters.LibraryConverter;
import ru.Irina.Library.exceptions.ResourceNotFoundException;
import ru.Irina.Library.exceptions.ValidationException;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.stream.Collectors;

public class LibraryService<T, ID, D> {
    protected final JpaRepository<T, ID> repository;
    protected final LibraryConverter<T, D> converter;
    protected final MessageSource messageSource;

    @Value("${pagination.page-size}")
    private int PAGE_SIZE;

    public LibraryService(JpaRepository<T, ID> repository, LibraryConverter<T, D> converter,
                          MessageSource messageSource) {
        this.repository = repository;
        this.converter = converter;
        this.messageSource = messageSource;
    }

    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(messageSource.getMessage("error.resource.id",
                        null, Locale.getDefault()), id))
        );
    }

    public Page<T> findAllPage(int page) {
        return repository.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    @Transactional
    public void deleteById(ID id) {
        repository.delete(findById(id));
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T add(D dto) {
        T entity = converter.dtoToEntity(null, dto);

        return save(entity);
    }

    @Transactional
    public T update(D dto, ID id) {
        T entity = converter.dtoToEntity(findById(id), dto);

        return save(entity);
    }

    public void checkValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
                            .collect(Collectors.toList())
            );
        }
    }

}
