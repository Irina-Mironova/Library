package ru.Irina.Library.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.Irina.Library.converters.AuthorConverter;
import ru.Irina.Library.entities.Author;
import ru.Irina.Library.services.AuthorService;
import ru.Irina.api.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Tag(name = "Авторы", description = "Методы работы с авторами")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorConverter authorConverter;

    @Operation(
            summary = "Запрос на получение автора по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AuthorDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable
                              @Parameter(description = "Идентификатор автора", required = true) Long id) {

        Author author = authorService.findById(id);

        return authorConverter.entityToDto(author);
    }


    @Operation(
            summary = "Запрос на поиск авторов по фамилии с постраничным выводом",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping("/name_page")
    public Page<AuthorDto> findByNamePage(
            @RequestParam(value = "name", required = true)
            @Parameter(description = "фамилия", required = true) String name,

            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        return authorService.findByNamePage(name, page - 1).map(authorConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на поиск авторов по фамилии",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping("/name")
    public List<AuthorDto> findByName(
            @RequestParam(value = "name", required = true)
            @Parameter(description = "фамилия", required = true) String name
    ) {
        return authorService.findByName(name).stream().map(authorConverter::entityToDto).collect(Collectors.toList());
    }


    @Operation(
            summary = "Запрос на вывод всех авторов постранично",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping()
    public Page<AuthorDto> findAllPage(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        return authorService.findAllPage(page - 1).map(authorConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на удаление автора по id",
            responses = {
                    @ApiResponse(
                            description = "Автор успешно удален", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Parameter(description = "Идентификатор автора", required = true) Long id) {
        authorService.deleteById(id);
    }


    @Operation(
            summary = "Запрос на добавление нового автора",
            responses = {
                    @ApiResponse(
                            description = "Автор успешно добавлен", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = AuthorDto.class))
                    )
            }
    )
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto add(@RequestBody @Validated AuthorDto authorDto, BindingResult bindingResult) {
        authorService.checkValidation(bindingResult);

        return authorConverter.entityToDto(authorService.add(authorDto));
    }


    @Operation(
            summary = "Запрос на обновление данных об авторе",
            responses = {
                    @ApiResponse(
                            description = "Информация об авторе успешно обновлена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AuthorDto.class))
                    )
            }
    )
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDto update(@RequestBody @Validated AuthorDto authorDto, BindingResult bindingResult) {
        authorService.checkValidation(bindingResult);

        return authorConverter.entityToDto(authorService.update(authorDto));
    }
}