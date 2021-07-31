package com.library.test;

import com.hotel.api.service.IAcceptanceService;
import com.hotel.api.service.IBookService;
import com.hotel.api.service.IReaderService;
import com.hotel.api.service.IWorkerService;
import com.library.dao.configuration.JpaConfiguration;
import com.library.model.Acceptance;
import com.library.model.Book;
import com.library.model.dto.AcceptanceDto;
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
public class AcceptanceServiceTest {

    @Autowired
    private IAcceptanceService acceptanceService;
    @Autowired
    private IWorkerService workerService;
    @Autowired
    private IReaderService readerService;
    @Autowired
    private IBookService bookService;

    @Test
    public void testCreate(){
        Acceptance acceptance=acceptanceService.addAcceptance(workerService.getById(15),readerService.getById(10), bookService.getById(11),1,LocalDate.of(2021,07,01));
        assertNotNull(acceptance);
    }

    @Test
    public void testGetById(){
        Acceptance acceptance=acceptanceService.getByIdUi(17);
        assertTrue(acceptance.getReader().getId()==10);
    }

    @Test
    public void testGetAll(){
        List<Acceptance> acceptance=acceptanceService.getAllUi();
        assertTrue(acceptance.size()>0);
    }

    @Test
    public void testUpdate(){
        Acceptance acceptance=acceptanceService.getByIdUi(18);
        acceptance.setSum(2);
        acceptanceService.updateAcceptance(18,acceptance);
        Acceptance updatedAcceptance=acceptanceService.getByIdUi(18);
        assertTrue(updatedAcceptance.getSum()==2);
    }

    @Test
    public void testDelete(){
        Acceptance acceptance=acceptanceService.getByIdUi(17);
        acceptanceService.deleteAcceptance(acceptance.getId());
        Acceptance deletedAcceptance=acceptanceService.getByIdUi(17);
        assertNull(deletedAcceptance);
    }
}
