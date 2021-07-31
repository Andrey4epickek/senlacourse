package com.library.controller;

import com.hotel.api.service.IBookService;
import com.library.model.Book;
import com.library.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping( "/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Integer id){
        log.info("received request: /books/"+id);
        BookDto dto= bookService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAll(){
        log.info("received request: /books/");
        List<BookDto> bookDtoList=bookService.getAll();
        return ResponseEntity.ok(bookDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody BookDto bookDto){
        log.info("received request: /add/"+bookDto);
        bookService.addBook(bookDto.getTitle(),bookDto.getData(),
                            bookDto.getAuthor(),bookDto.getGod(),
                            bookDto.getGenre(),bookDto.getSum());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto){
        log.info("received request: /update/"+bookDto);
        bookService.updateBookDto(bookDto.getId(),bookDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<BookDto> delete(@RequestParam (name = "id") Integer id){
        log.info("received request: /delete/"+id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
