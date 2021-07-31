package com.library.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class AcceptanceDto {
    private Integer id;
    private WorkerDto workerDto;
    private ReaderDto readerDto;
    private BookDto bookDto;
    private Integer sum;
    private LocalDate data;
}
