package com.library.test;

import com.hotel.api.dao.IReaderDao;
import com.hotel.api.service.IReaderService;
import com.library.dao.configuration.JpaConfiguration;
import com.library.model.Reader;
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
public class ReaderServiceTest {

    @Autowired
    private IReaderService readerService;

    @Test
    public void testCreate(){
        Reader reader=readerService.addReader("Petrov","Petr","Petrovich",20, LocalDate.of(2021,07,01));
        assertNotNull(reader);
    }

    @Test
    public void testGetById(){
        Reader reader=readerService.getByIdUi(10);
        assertEquals("Petr",reader.getFirstName());
    }

    @Test
    public void testGetAll(){
        List<Reader> readers=readerService.getAllUi();
        assertTrue(readers.size()>0);
    }

    @Test
    public void testUpdate(){
        Reader reader=readerService.getByIdUi(10);
        reader.setAge(25);
        readerService.updateReader(10,reader);
        Reader updatedReader=readerService.getByIdUi(10);
        assertTrue(updatedReader.getAge()==25);
    }

    @Test
    public void testDelete(){
        Reader reader=readerService.getByIdUi(9);
        readerService.deleteReader(reader.getId());
        Reader deletedReader=readerService.getByIdUi(9);
        assertNull(deletedReader);
    }
}
