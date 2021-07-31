package com.library.facade;

import com.hotel.api.service.*;
import com.library.model.*;
import com.library.model.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryFacade {

    private final IReaderService readerService;
    private final IWorkerService workerService;
    private final IBookService bookService;
    private final IIssuanceService issuanceService;
    private final IAcceptanceService acceptanceService;
    private final ModelMapper mapper;

    public LibraryFacade(IReaderService readerService,IWorkerService workerService,IBookService bookService,IIssuanceService issuanceService,IAcceptanceService acceptanceService,ModelMapper mapper){
        this.readerService=readerService;
        this.workerService=workerService;
        this.bookService=bookService;
        this.issuanceService=issuanceService;
        this.acceptanceService=acceptanceService;
        this.mapper=mapper;
    }

    /**
     *Reader
     */
    public Reader addReader(String lastName,String firstName,String patronymic, Integer age, LocalDate data){
        Reader reader=readerService.addReader(lastName,firstName,patronymic,age,data);
        return reader;
    }

    public void deleteReader(Integer readerId){
        readerService.deleteReader(readerId);
    }

    public ReaderDto getReader(Integer readerId){
        ReaderDto readerDto=readerService.getById(readerId);
        return readerDto;
    }

    public Reader getReaderUi(Integer readerId){
        Reader reader=readerService.getByIdUi(readerId);
        return reader;
    }

    public void updateReader(Integer readerId,Reader reader){readerService.updateReader(readerId,reader);
    }

    public List<Reader> getAllReaders(){
        List<ReaderDto> readerDtoList=readerService.getAll();
        List<Reader> readerList=new ArrayList<>();
        for(ReaderDto readerDto:readerDtoList){
            Reader reader=mapper.map(readerDto,Reader.class);
            readerList.add(reader);
        }
        return readerList;
    }

    /**
     *Worker
     */
    public Worker addWorker(String lastName, String firstName, String patronymic, LocalDate data){
        Worker worker=workerService.addWorker(lastName,firstName,patronymic,data);
        return worker;
    }

    public void deleteWorker(Integer workerId){
        workerService.deleteWorker(workerId);
    }

    public WorkerDto getWorker(Integer workerId){
        WorkerDto workerDto=workerService.getById(workerId);
        return workerDto;
    }

    public Worker getWorkerUi(Integer workerId){
        Worker worker=workerService.getByIdUi(workerId);
        return worker;
    }

    public void updateWorker(Integer workerId,Worker worker){workerService.updateWorker(workerId,worker);};

    public List<Worker> getAllWorkers(){
        List<WorkerDto> workerDtoList=workerService.getAll();
        List<Worker> workerList=new ArrayList<>();
        for(WorkerDto workerDto:workerDtoList){
            Worker worker=mapper.map(workerDto,Worker.class);
            workerList.add(worker);
        }
        return workerList;
    }

    /**
     *Book
     */
    public Book addBook(String title, LocalDate data, String author, Integer god, String genre, Integer sum){
        Book book=bookService.addBook(title,data,author,god,genre,sum);
        return book;
    }

    public void deleteBook(Integer bookId){
        bookService.deleteBook(bookId);
    }

    public BookDto getBook(Integer bookId){
        BookDto bookDto=bookService.getById(bookId);
        return bookDto;
    }

    public Book getBookUi(Integer bookId){
        Book book=bookService.getByIdUi(bookId);
        return book;
    }

    public void updateBook(Integer bookId,Book book){bookService.updateBook(bookId,book);}

    public List<Book> getAllBooks(){
        List<BookDto> bookDtoList=bookService.getAll();
        List<Book> bookList=new ArrayList<>();
        for(BookDto bookDto:bookDtoList){
            Book book=mapper.map(bookDto,Book.class);
            bookList.add(book);
        }
        return bookList;
    }

    public List<Book> findExpiredBooks(){return bookService.findExpiredBooks();}

    /**
     *Issuance
     */
    public Issuance addIssuance(WorkerDto workerDto, ReaderDto readerDto, BookDto bookDto, Integer sum, Integer time,LocalDate data){
        Issuance issuance=issuanceService.addIssuance(workerDto,readerDto,bookDto,sum,time,data);
        return issuance;
    }

    public void deleteIssuance(Integer issuanceId){
        issuanceService.deleteIssuance(issuanceId);
    }

    public Issuance getIssuance(Integer issuanceId){
        Issuance issuance=issuanceService.getByIdUi(issuanceId);
        return issuance;
    }

    public void updateIssuance(Integer issuanceId,Issuance issuance){issuanceService.updateIssuance(issuanceId,issuance);}

    public List<Issuance> getAllIssuance(){
        return issuanceService.getAllUi();
    }

    /**
     *Acceptance
     */
    public Acceptance addAcceptance(WorkerDto workerDto, ReaderDto readerDto, BookDto bookDto, Integer sum ,LocalDate data){
        Acceptance acceptance=acceptanceService.addAcceptance(workerDto,readerDto,bookDto,sum,data);
        return acceptance;
    }

    public void deleteAcceptance(Integer acceptanceId){
        acceptanceService.deleteAcceptance(acceptanceId);
    }

    public Acceptance getAcceptance(Integer acceptanceId){
        Acceptance acceptance=acceptanceService.getByIdUi(acceptanceId);
        return acceptance;
    }

    public void updateAcceptance(Integer acceptanceId,Acceptance acceptance){acceptanceService.updateAcceptance(acceptanceId,acceptance);}

    public List<Acceptance> getAllAcceptance(){
        return acceptanceService.getAllUi();
    }

}
