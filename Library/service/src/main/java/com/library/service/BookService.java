package com.library.service;

import com.hotel.api.dao.IBookDao;
import com.hotel.api.service.IBookService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.Book;
import com.library.model.Reader;
import com.library.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.toIntExact;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements IBookService {

    private static final Logger LOGGER= LogManager.getLogger(BookService.class.getName());
    private final IBookDao bookDao;
    private final ModelMapper mapper;

    @Override
    public Book addBook(String title, LocalDate data, String author, Integer god, String genre, Integer sum) {
        try {
            LOGGER.info(String.format("Adding of book %s,genre %s",title,genre));
            Book book=new Book();
            book.setTitle(title);
            book.setData(data);
            book.setAuthor(author);
            book.setGod(god);
            book.setGenre(genre);
            book.setSum(sum);
            bookDao.save(book);
            return book;
        } catch (DaoException e) {
            LOGGER.warn("Adding of book failed",e);
            throw new ServiceException("Adding of book failed",e);
        }
    }

    @Override
    public BookDto getById(Integer bookId) {
        try {
            LOGGER.info(String.format("Getting of book %d",bookId));
            Book book=bookDao.getById(bookId);
            return mapper.map(book,BookDto.class);
        } catch (DaoException e) {
            LOGGER.warn("Getting of book failed",e);
            throw new ServiceException("Getting of book failed",e);
        }
    }

    @Override
    public Book getByIdUi(Integer bookId) {
        try {
            LOGGER.info(String.format("Getting of book %d",bookId));
            Book book=bookDao.getById(bookId);
            return book;
        } catch (DaoException e) {
            LOGGER.warn("Getting of book failed",e);
            throw new ServiceException("Getting of book failed",e);
        }
    }

    @Override
    public List<Book> findExpiredBooks() {
        try{
            LOGGER.info(String.format("Finding of expired books"));
            List<Book> bookList=bookDao.getAll();
            List<Book> expiredBooks=new ArrayList<>();
            for(Book book:bookList){
                if(book.getIssuance().getTime()<(toIntExact(Duration.between(book.getIssuance().getData().atStartOfDay(),book.getAcceptance().getData().atStartOfDay()).toDays()))){
                    expiredBooks.add(book);
                }
            }
            return expiredBooks;
        } catch (DaoException e) {
            LOGGER.warn("Finding of expired books failed",e);
            throw new ServiceException("Finding of expired books failed",e);
        }
    }

    @Override
    public List<BookDto> getAll() {
        try{
            LOGGER.info(String.format("Getting of all books"));
            List<Book> bookList=bookDao.getAll();
            List<BookDto> bookDtoList=new ArrayList<>();
            for(Book book:bookList){
                BookDto bookDto=mapper.map(book,BookDto.class);
                bookDtoList.add(bookDto);
            }
            return bookDtoList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all books failed",e);
            throw new ServiceException("Getting of all books failed",e);
        }
    }

    @Override
    public List<Book> getAllUi() {
        try{
            LOGGER.info(String.format("Getting of all books"));
            List<Book> bookList=bookDao.getAll();
            return bookList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all books failed",e);
            throw new ServiceException("Getting of all books failed",e);
        }
    }

    @Override
    public void deleteBook(Integer bookId) {
        try{
            LOGGER.info(String.format("Deleting of book %d",bookId));
            Book book=bookDao.getById(bookId);
            bookDao.delete(book);
        } catch (DaoException e) {
            LOGGER.warn("Deleting of book failed",e);
            throw new ServiceException("Deleting of book failed",e);
        }
    }

    @Override
    public void updateBook(Integer bookId, Book book) {
        try {
            LOGGER.info(String.format("Updating of book %d",bookId));
            Book bookGet=bookDao.getById(bookId);
            bookGet.setTitle(book.getTitle());
            bookGet.setData(book.getData());
            bookGet.setAuthor(book.getAuthor());
            bookGet.setGod(book.getGod());
            bookGet.setGenre(book.getGenre());
            bookGet.setSum(book.getSum());
            bookDao.update(bookGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of book failed",e);
            throw new ServiceException("Updating of book failed",e);
        }
    }

    @Override
    public void updateBookDto(Integer bookId, BookDto bookDto) {
        try {
            LOGGER.info(String.format("Updating of book %d",bookId));
            Book bookGet=bookDao.getById(bookId);
            bookGet.setTitle(bookDto.getTitle());
            bookGet.setData(bookDto.getData());
            bookGet.setAuthor(bookDto.getAuthor());
            bookGet.setGod(bookDto.getGod());
            bookGet.setGenre(bookDto.getGenre());
            bookGet.setSum(bookDto.getSum());
            bookDao.update(bookGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of book failed",e);
            throw new ServiceException("Updating of book failed",e);
        }
    }
}
