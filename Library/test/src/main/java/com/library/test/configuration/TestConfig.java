package com.library.test.configuration;

import com.hotel.api.dao.*;
import com.hotel.api.service.IAcceptanceService;
import com.hotel.api.service.IBookService;
import com.hotel.api.service.IReaderService;
import com.hotel.api.service.IWorkerService;
import com.library.service.AcceptanceService;
import com.library.service.BookService;
import com.library.service.ReaderService;
import com.library.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.library.dao","com.library.service"})
public class TestConfig {

    private  IReaderDao readerDao;
    private IBookDao bookDao;
    private IWorkerDao workerDao;
    private IAcceptanceDao acceptanceDao;
    private IIssuanceDao issuanceDao;
    private ModelMapper mapper;

    @Bean
    public IReaderService getReaderService(){
        return new ReaderService(readerDao,mapper);
    }

    @Bean
    public IBookService getBookService(){
        return new BookService(bookDao,mapper);
    }

    @Bean
    public IWorkerService getWorkerService(){
        return new WorkerService(workerDao,mapper);
    }

    @Bean
    IAcceptanceService getAcceptanceService(){
        return new AcceptanceService(acceptanceDao,mapper);
    }
}
