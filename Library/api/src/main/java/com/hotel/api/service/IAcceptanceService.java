package com.hotel.api.service;

import com.library.model.Acceptance;
import com.library.model.dto.AcceptanceDto;
import com.library.model.dto.BookDto;
import com.library.model.dto.ReaderDto;
import com.library.model.dto.WorkerDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IAcceptanceService {
    Acceptance addAcceptance(WorkerDto workerDto, ReaderDto readerDto, BookDto bookDto, Integer sum, LocalDate data);
    AcceptanceDto getById(Integer acceptanceId);
    Acceptance getByIdUi(Integer acceptanceId);
    void updateAcceptance(Integer acceptanceId,Acceptance acceptance);
    void updateAcceptanceDto(Integer acceptanceId,AcceptanceDto acceptanceDto);
    List<AcceptanceDto> getAll();
    List<Acceptance> getAllUi();
    void deleteAcceptance(Integer acceptanceId);
}
