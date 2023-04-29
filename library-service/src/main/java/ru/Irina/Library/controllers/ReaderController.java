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
import ru.Irina.Library.converters.ReaderConverter;
import ru.Irina.Library.entities.Reader;
import ru.Irina.Library.services.ReaderService;
import ru.Irina.api.ReaderDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/readers")
@RequiredArgsConstructor
@Tag(name = "Читатели", description = "Методы работы с читателями")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderConverter readerConverter;

    @Operation(
            summary = "Запрос на получение читателя по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ReaderDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ReaderDto findById(@PathVariable
                              @Parameter(description = "Идентификатор читателя", required = true) Long id) {

        Reader reader = readerService.findById(id);

        return readerConverter.entityToDto(reader);
    }


    @Operation(
            summary = "Запрос на поиск читателя по фамилии с постраничным выводом",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping("/name_page")
    public Page<ReaderDto> findByNamePage(
            @RequestParam(value = "name", required = true)
            @Parameter(description = "фамилия", required = true) String name,

            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        return readerService.findByNamePage(name, page - 1).map(readerConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на поиск читателей по фамилии",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping("/name")
    public List<ReaderDto> findByName(
            @RequestParam(value = "name", required = true)
            @Parameter(description = "фамилия", required = true) String name
    ) {
        return readerService.findByName(name).stream().map(readerConverter::entityToDto).collect(Collectors.toList());
    }


    @Operation(
            summary = "Запрос на вывод всех читателей постранично",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping()
    public Page<ReaderDto> findAllPage(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Parameter(description = "номер страницы", required = false) int page) {

        if (page <= 0) {
            page = 1;
        }

        return readerService.findAllPage(page - 1).map(readerConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на удаление читателя по id",
            responses = {
                    @ApiResponse(
                            description = "Читатель успешно удален", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Parameter(description = "Идентификатор читателя", required = true) Long id) {
        readerService.deleteById(id);
    }

    @Operation(
            summary = "Запрос на добавление нового читателя",
            responses = {
                    @ApiResponse(
                            description = "Читатель успешно добавлен", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ReaderDto.class))
                    )
            }
    )
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDto add(@RequestBody @Validated ReaderDto readerDto, BindingResult bindingResult) {
        readerService.checkValidation(bindingResult);

        return readerConverter.entityToDto(readerService.add(readerDto));
    }


    @Operation(
            summary = "Запрос на обновление данных о читателе",
            responses = {
                    @ApiResponse(
                            description = "Информация о читателе успешно обновлена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ReaderDto.class))
                    )
            }
    )
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDto update(@RequestBody @Validated ReaderDto readerDto, BindingResult bindingResult) {
        readerService.checkValidation(bindingResult);

        return readerConverter.entityToDto(readerService.update(readerDto));
    }
}

