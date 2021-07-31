package com.library.controller;

import com.hotel.api.service.IAcceptanceService;
import com.library.model.dto.AcceptanceDto;
import com.library.model.dto.BookDto;
import com.library.model.dto.IssuanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping( "/acceptance")
@RequiredArgsConstructor
public class AcceptanceController {

    private final IAcceptanceService acceptanceService;

    @GetMapping("/{id}")
    public ResponseEntity<AcceptanceDto> getById(@PathVariable Integer id){
        log.info("received request: /acceptance/"+id);
        AcceptanceDto dto= acceptanceService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AcceptanceDto>> getAll(){
        log.info("received request: /acceptance/");
        List<AcceptanceDto> acceptanceDtoList=acceptanceService.getAll();
        return ResponseEntity.ok(acceptanceDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody AcceptanceDto acceptanceDto){
        log.info("received request: /add/"+acceptanceDto);
        acceptanceService.addAcceptance(acceptanceDto.getWorkerDto(),acceptanceDto.getReaderDto(),
                                        acceptanceDto.getBookDto(),acceptanceDto.getSum(),
                                        acceptanceDto.getData());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<AcceptanceDto> update(@RequestBody AcceptanceDto acceptanceDto){
        log.info("received request: /update/"+acceptanceDto);
        acceptanceService.updateAcceptanceDto(acceptanceDto.getId(),acceptanceDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<AcceptanceDto> delete(@RequestParam (name = "id") Integer id){
        log.info("received request: /delete/"+id);
        acceptanceService.deleteAcceptance(id);
        return ResponseEntity.noContent().build();
    }
}
