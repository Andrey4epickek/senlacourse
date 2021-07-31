package com.library.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class WorkerDto {
    private Integer id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate data;
}
