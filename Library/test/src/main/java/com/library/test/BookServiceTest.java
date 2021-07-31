package com.library.test;

import com.hotel.api.service.IBookService;
import com.library.dao.configuration.JpaConfiguration;
import com.library.model.Book;
import com.library.test.configuration.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, TestConfig.class})
public class BookServiceTest {

    @Autowired
    private IBookService bookService;

    @Test
    public void testCreate(){
        Book book=bookService.addBook("Show",LocalDate.of(2021,07,01),"Pushkin",2012,"Drama",123);
        assertNotNull(book);
    }

    @Test
    public void testGetById(){
        Book book=bookService.getByIdUi(11);
        assertEquals("Drama",book.getGenre());
    }

    @Test
    public void testGetAll(){
        List<Book> books=bookService.getAllUi();
        assertTrue(books.size()>0);
    }

    @Test
    public void testUpdate(){
        Book book=bookService.getByIdUi(11);
        book.setSum(100);
        bookService.updateBook(11,book);
        Book updatedBook=bookService.getByIdUi(11);
        assertTrue(updatedBook.getSum()==100);
    }

    @Test
    public void testDelete(){
        Book book=bookService.getByIdUi(13);
        bookService.deleteBook(book.getId());
        Book deletedBook=bookService.getByIdUi(13);
        assertNull(deletedBook);
    }
}
