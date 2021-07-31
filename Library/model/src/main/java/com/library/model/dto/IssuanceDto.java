package com.library.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssuanceDto {
    private Integer id;
    private WorkerDto workerDto;
    private ReaderDto readerDto;
    private BookDto bookDto;
    private Integer sum;
    private Integer time;
    private LocalDate data;
}
