package com.hotel.api.service;

import com.library.model.Reader;
import com.library.model.dto.ReaderDto;

import java.time.LocalDate;
import java.util.List;

public interface IReaderService {
    Reader addReader( String lastName,String firstName,String patronymic, Integer age, LocalDate data);
    ReaderDto getById(Integer readerId);
    Reader getByIdUi(Integer readerId);
    void updateReader(Integer readerId,Reader reader);
    void updateReaderDto(Integer readerId,ReaderDto readerDto);
    List<ReaderDto> getAll();
    List<Reader> getAllUi();
    void deleteReader(Integer readerId);
}
