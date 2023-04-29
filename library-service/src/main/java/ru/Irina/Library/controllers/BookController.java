package ru.Irina.Library.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.Irina.Library.converters.BookConverter;
import ru.Irina.Library.entities.Book;
import ru.Irina.Library.services.BookService;
import ru.Irina.api.BookDto;
import ru.Irina.api.BookFilter;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Tag(name = "Книги", description = "Методы работы с книгами")
public class BookController {
    private final BookService bookService;
    private final BookConverter bookConverter;

    @Operation(
            summary = "Запрос на получение книги по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public BookDto findById(@PathVariable
                            @Parameter(description = "Идентификатор книги", required = true) Long id) {

        Book book = bookService.findById(id);

        return bookConverter.entityToDto(book);
    }


    @Operation(
            summary = "Запрос на поиск книг по автору и названию с постраничным выводом",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @PostMapping("/filter_page")
    public Page<BookDto> findByNamePage(
            @RequestBody BookFilter bookFilter,
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        Specification<Book> specification = bookService.createSpecByFilters(bookFilter);

        return bookService.findByNameAndTitlePage(specification, page - 1)
                .map(bookConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на вывод всех книг постранично",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping()
    public Page<BookDto> findAllPage(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        return bookService.findAllPage(page - 1).map(bookConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на удаление книги по id",
            responses = {
                    @ApiResponse(
                            description = "Книга успешно удалена", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Parameter(description = "Идентификатор книги", required = true) Long id) {
        bookService.deleteById(id);
    }


    @Operation(
            summary = "Запрос на добавление новой книги",
            responses = {
                    @ApiResponse(
                            description = "Книга успешно добавлена", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = BookDto.class))
                    )
            }
    )
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto add(@RequestBody @Validated BookDto bookDto, BindingResult bindingResult) {
        bookService.checkValidation(bindingResult);

        return bookConverter.entityToDto(bookService.add(bookDto));
    }


    @Operation(
            summary = "Запрос на обновление данных о книге",
            responses = {
                    @ApiResponse(
                            description = "Информация о книге успешно обновлена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDto.class))
                    )
            }
    )
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public BookDto update(@RequestBody @Validated BookDto bookDto, BindingResult bindingResult) {
        bookService.checkValidation(bindingResult);

        return bookConverter.entityToDto(bookService.update(bookDto));
    }
}
