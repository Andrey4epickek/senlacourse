package com.library.controller;

import com.hotel.api.service.IWorkerService;
import com.library.model.dto.BookDto;
import com.library.model.dto.ReaderDto;
import com.library.model.dto.WorkerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping( "/workers")
@RequiredArgsConstructor
public class WorkerController {

    private final IWorkerService workerService;

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDto> getById(@PathVariable Integer id){
        log.info("received request: /workers/"+id);
        WorkerDto dto= workerService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkerDto>> getAll(){
        log.info("received request: /workers/");
        List<WorkerDto> workerDtoList=workerService.getAll();
        return ResponseEntity.ok(workerDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody WorkerDto workerDto){
        log.info("received request: /add/"+workerDto);
        workerService.addWorker(workerDto.getLastName(),workerDto.getFirstName(),
                                workerDto.getPatronymic(),workerDto.getData());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<WorkerDto> update(@RequestBody WorkerDto workerDto){
        log.info("received request: /update/"+workerDto);
        workerService.updateWorkerDto(workerDto.getId(),workerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<WorkerDto> delete(@RequestParam (name = "id") Integer id){
        log.info("received request: /delete/"+id);
        workerService.deleteWorker(id);
        return ResponseEntity.noContent().build();
    }
}
