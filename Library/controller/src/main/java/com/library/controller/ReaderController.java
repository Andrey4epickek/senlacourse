package com.library.controller;

import com.hotel.api.service.IReaderService;
import com.library.model.dto.BookDto;
import com.library.model.dto.ReaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping( "/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final IReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getById(@PathVariable Integer id){
        log.info("received request: /readers/"+id);
        ReaderDto dto= readerService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReaderDto>> getAll(){
        log.info("received request: /readers/");
        List<ReaderDto> readerDtoList=readerService.getAll();
        return ResponseEntity.ok(readerDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody ReaderDto readerDto) {
        log.info("received request: /add/"+readerDto);
        readerService.addReader(readerDto.getLastName(),readerDto.getFirstName(),
                                readerDto.getPatronymic(),readerDto.getAge(),readerDto.getData());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ReaderDto> update(@RequestBody ReaderDto readerDto){
        log.info("received request: /update/"+readerDto);
        readerService.updateReaderDto(readerDto.getId(),readerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ReaderDto> delete(@RequestParam (name = "id") Integer id){
        log.info("received request: /delete/"+id);
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }
}
