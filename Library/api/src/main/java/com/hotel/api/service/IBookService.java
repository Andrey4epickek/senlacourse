package com.hotel.api.service;

import com.library.model.Book;
import com.library.model.dto.BookDto;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {

    Book addBook(String title, LocalDate data, String author, Integer god, String genre, Integer sum);
    BookDto getById(Integer bookId);
    Book getByIdUi(Integer bookId);
    List<Book> findExpiredBooks();
    List<BookDto> getAll();
    List<Book> getAllUi();
    void deleteBook(Integer bookId);
    void updateBook(Integer bookId, Book book);
    void updateBookDto(Integer bookId,BookDto bookDto);
}
