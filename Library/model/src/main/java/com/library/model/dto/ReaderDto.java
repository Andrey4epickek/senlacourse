package com.library.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReaderDto {
    private Integer id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Integer age;
    private LocalDate data;
}
