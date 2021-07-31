package com.library.test;

import com.hotel.api.service.IWorkerService;
import com.library.dao.configuration.JpaConfiguration;
import com.library.model.Reader;
import com.library.model.Worker;
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
public class WorkerServiceTest {

    @Autowired
    private IWorkerService workerService;

    @Test
    public void testCreate(){
        Worker worker=workerService.addWorker("Petrov","Petr","Petrovich", LocalDate.of(2021,07,01));
        assertNotNull(worker);
    }

    @Test
    public void testGetById(){
        Worker worker=workerService.getByIdUi(16);
        assertEquals("Petrovich",worker.getPatronymic());
    }

    @Test
    public void testGetAll(){
        List<Worker> workers=workerService.getAllUi();
        assertTrue(workers.size()>0);
    }

    @Test
    public void testUpdate(){
        Worker worker=workerService.getByIdUi(15);
        worker.setLastName("Pupkin");
        workerService.updateWorker(15,worker);
        Worker updatedWorker=workerService.getByIdUi(15);
        assertEquals("Pupkin",updatedWorker.getLastName());
    }

    @Test
    public void testDelete(){
        Worker worker=workerService.getByIdUi(16);
        workerService.deleteWorker(worker.getId());
        Worker deletedWorker=workerService.getByIdUi(16);
        assertNull(deletedWorker);
    }
}
