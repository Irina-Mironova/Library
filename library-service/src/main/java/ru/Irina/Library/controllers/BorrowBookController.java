package ru.Irina.Library.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.Irina.Library.services.BorrowBookService;

@RestController
@RequestMapping("/api/v1/borrow_book")
@Tag(name = "Выдача книг", description = "Методы работы библиотечного сервиса")
public class BorrowBookController {
    private final BorrowBookService borrowBookService;

    public BorrowBookController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @Operation(
            summary = "Запрос на выдачу книги читателю",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/take")
    @ResponseStatus(HttpStatus.OK)
    public void takeBook(
            @RequestParam(value = "book_id", required = true)
            @Parameter(description = "идентификатор книги", required = true) Long bookId,

            @RequestParam(value = "reader_id", required = true)
            @Parameter(description = "идентификатор читателя", required = true) Long readerId) {

        borrowBookService.takeBook(bookId, readerId);
    }


    @Operation(
            summary = "Запрос на возврат книги в библиотеку",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/give")
    @ResponseStatus(HttpStatus.OK)
    public void giveBook(
            @RequestParam(value = "book_id", required = true)
            @Parameter(description = "идентификатор книги", required = true) Long bookId,

            @RequestParam(value = "reader_id", required = true)
            @Parameter(description = "идентификатор читателя", required = true) Long readerId) {

        borrowBookService.giveBook(bookId, readerId);
    }
}
