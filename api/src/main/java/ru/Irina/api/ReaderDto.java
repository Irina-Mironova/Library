package ru.Irina.api;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Модель данных читателя")
public class ReaderDto {

    @Schema(description = "Id читателя", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long readerId;

    @Schema(description = "ФИО читателя", maxLength = 255, minLength = 3, required = true, example = "Иванов Иван Иванович")
    @Size(min = 3, max = 255, message = "{valid.message.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String readerName;

    @Schema(description = "Дата рождения читателя", required = true, example = "1994-05-26")
    @Past(message = "{valid.message.dateBirth}")
    @NotNull(message = "{valid.message.empty}")
    private LocalDate dtBirth;

    @Schema(description = "Адрес", required = true, example = "Москва, ул.Ленина,15, кв.6")
    @Size(min = 3, max = 255, message = "{valid.message.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String address;

    @Schema(description = "Телефон", required = true, example = "+79174363697")
    @Pattern(regexp = "^(\\+7)([0-9]{10})$", message = "{valid.message.phone}")
    @NotBlank(message = "{valid.message.empty}")
    private String phone;

    public ReaderDto() {
    }

    public ReaderDto(Long readerId, String readerName, LocalDate dtBirth, String address, String phone) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.dtBirth = dtBirth;
        this.address = address;
        this.phone = phone;
    }

    public ReaderDto(String readerName, LocalDate dtBirth, String address, String phone) {
        this.readerName = readerName;
        this.dtBirth = dtBirth;
        this.address = address;
        this.phone = phone;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public LocalDate getDtBirth() {
        return dtBirth;
    }

    public void setDtBirth(LocalDate dtBirth) {
        this.dtBirth = dtBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
