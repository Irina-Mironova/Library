package ru.Irina.api;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Модель данных автора")
public class AuthorDto {

    @Schema(description = "Id автора", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long authorId;

    @Schema(description = "ФИО автора", maxLength = 255, minLength = 3, required = true, example = "Пушкин Александр Сергеевич")
    @Size(min = 3, max = 255, message = "{valid.message.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String authorName;

    @Schema(description = "Описание", maxLength = 255, required = false, example = "поэт, писатель")
    private String description;


    public AuthorDto() {
    }

    public AuthorDto(Long authorId, String authorName, String description) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.description = description;
    }

    public AuthorDto(String authorName, String description) {
        this.authorName = authorName;
        this.description = description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
