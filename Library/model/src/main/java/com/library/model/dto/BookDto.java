package com.library.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BookDto {
    private Integer id;
    private String title;
    private LocalDate data;
    private String author;
    private Integer god;
    private String genre;
    private Integer sum;
}
