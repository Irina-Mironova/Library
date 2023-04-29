package ru.Irina.api;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Модель данных книги")
public class BookDto {

    @Schema(description = "Id книги", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long bookId;

    @Schema(description = "Id автора", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long authorId;

    @Schema(description = "ФИО автора", maxLength = 255, minLength = 3, required = true, example = "Пушкин Александр Сергеевич")
    private String authorName;

    @Schema(description = "Название книги", maxLength = 255, minLength = 3, required = true, example = "Евгений Онегин")
    @Size(min = 3, max = 255, message = "{valid.message.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String title;

    @Schema(description = "Год издания книги", required = true, example = "1999")
    @Min(value = 1800, message = "{valid.message.year}")
    @NotNull(message = "{valid.message.empty}")
    private Integer yearPublish;

    @Schema(description = "Количество экземпляров книги", required = true, example = "1")
    @Min(value = 1, message = "{valid.message.amount}")
    @NotNull(message = "{valid.message.empty}")
    private Integer amount;

    public BookDto() {
    }

    public BookDto(Long bookId, Long authorId, String authorName, String title, Integer yearPublish, Integer amount) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.title = title;
        this.yearPublish = yearPublish;
        this.amount = amount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearPublish() {
        return yearPublish;
    }

    public void setYearPublish(Integer yearPublish) {
        this.yearPublish = yearPublish;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
