package com.library.test;

import com.hotel.api.service.IBookService;
import com.hotel.api.service.IIssuanceService;
import com.hotel.api.service.IReaderService;
import com.hotel.api.service.IWorkerService;
import com.library.dao.configuration.JpaConfiguration;
import com.library.model.Acceptance;
import com.library.model.Issuance;
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
public class IssuanceServiceTest {

    @Autowired
    private IIssuanceService issuanceService;
    @Autowired
    private IWorkerService workerService;
    @Autowired
    private IReaderService readerService;
    @Autowired
    private IBookService bookService;

    @Test
    public void testCreate(){
        Issuance issuance=issuanceService.addIssuance(workerService.getById(15),readerService.getById(10), bookService.getById(11),1,1, LocalDate.of(2021,07,01));
        assertNotNull(issuance);
    }

    @Test
    public void testGetById(){
        Issuance issuance=issuanceService.getByIdUi(19);
        assertTrue(issuance.getReader().getId()==10);
    }

    @Test
    public void testGetAll(){
        List<Issuance> issuance=issuanceService.getAllUi();
        assertTrue(issuance.size()>0);
    }

    @Test
    public void testUpdate(){
        Issuance issuance=issuanceService.getByIdUi(19);
        issuance.setTime(5);
        issuanceService.updateIssuance(19,issuance);
        Issuance updatedIssuance=issuanceService.getByIdUi(19);
        assertTrue(updatedIssuance.getTime()==5);
    }

    @Test
    public void testDelete(){
        Issuance issuance=issuanceService.getByIdUi(19);
        issuanceService.deleteIssuance(issuance.getId());
        Issuance deletedIssuance=issuanceService.getByIdUi(19);
        assertNull(deletedIssuance);
    }

}
