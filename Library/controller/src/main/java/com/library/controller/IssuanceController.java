package com.library.controller;

import com.hotel.api.service.IIssuanceService;
import com.library.model.dto.BookDto;
import com.library.model.dto.IssuanceDto;
import com.library.model.dto.WorkerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping( "/issuance")
@RequiredArgsConstructor
public class IssuanceController {

    private final IIssuanceService issuanceService;

    @GetMapping("/{id}")
    public ResponseEntity<IssuanceDto> getById(@PathVariable Integer id){
        log.info("received request: /issuance/"+id);
        IssuanceDto dto= issuanceService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IssuanceDto>> getAll(){
        log.info("received request: /issuance/");
        List<IssuanceDto> issuanceDtoList=issuanceService.getAll();
        return ResponseEntity.ok(issuanceDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody IssuanceDto issuanceDto){
        log.info("received request: /add/"+issuanceDto);
        issuanceService.addIssuance(issuanceDto.getWorkerDto(),issuanceDto.getReaderDto(),
                                    issuanceDto.getBookDto(),issuanceDto.getSum(),
                                    issuanceDto.getTime(),issuanceDto.getData());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<IssuanceDto> update(@RequestBody IssuanceDto issuanceDto){
        log.info("received request: /update/"+issuanceDto);
        issuanceService.updateIssuanceDto(issuanceDto.getId(),issuanceDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<IssuanceDto> delete(@RequestParam (name = "id") Integer id){
        log.info("received request: /delete/"+id);
        issuanceService.deleteIssuance(id);
        return ResponseEntity.noContent().build();
    }
}
