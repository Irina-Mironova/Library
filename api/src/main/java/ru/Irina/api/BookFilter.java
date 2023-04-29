package ru.Irina.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Фильтр для поиска книг по автору и/или названию")
public class BookFilter {

    @Schema(description = "ФИО автора", maxLength = 255, minLength = 3, required = false, example = "Пушкин Александр Сергеевич")
    private String authorName;

    @Schema(description = "Название книги", maxLength = 255, minLength = 3, required = false, example = "Евгений Онегин")
    private String title;


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


