package com.library.service;

import com.hotel.api.dao.IWorkerDao;
import com.hotel.api.service.IWorkerService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.model.dto.WorkerDto;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkerService implements IWorkerService {

    private static final Logger LOGGER= LogManager.getLogger(WorkerService.class.getName());
    private final IWorkerDao workerDao;
    private final ModelMapper mapper;

    @Override
    public Worker addWorker(String lastName, String firstName, String patronymic, LocalDate data) {
        try {
            LOGGER.info(String.format("Adding of worker %s %s",lastName,firstName));
            Worker worker=new Worker();
            worker.setLastName(lastName);
            worker.setFirstName(firstName);
            worker.setPatronymic(patronymic);
            worker.setData(data);
            workerDao.save(worker);
            return worker;
        } catch (DaoException e) {
            LOGGER.warn("Adding of worker failed",e);
            throw new ServiceException("Adding of worker failed",e);
        }
    }

    @Override
    public WorkerDto getById(Integer workerId) {
        try {
            LOGGER.info(String.format("Getting of worker %d",workerId));
            Worker worker =workerDao.getById(workerId);
            return mapper.map(worker, WorkerDto.class);
        } catch (DaoException e) {
            LOGGER.warn("Getting of worker failed",e);
            throw new ServiceException("Getting of worker failed",e);
        }
    }

    @Override
    public Worker getByIdUi(Integer workerId) {
        try {
            LOGGER.info(String.format("Getting of worker %d",workerId));
            Worker worker =workerDao.getById(workerId);
            return worker;
        } catch (DaoException e) {
            LOGGER.warn("Getting of worker failed",e);
            throw new ServiceException("Getting of worker failed",e);
        }
    }

    @Override
    public void updateWorker(Integer workerId, Worker worker) {
        try {
            LOGGER.info(String.format("Updating of worker %d",workerId));
            Worker workerGet=workerDao.getById(workerId);
            workerGet.setLastName(worker.getLastName());
            workerGet.setFirstName(worker.getFirstName());
            workerGet.setPatronymic(worker.getPatronymic());
            workerGet.setData(worker.getData());
            workerDao.update(workerGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of worker failed",e);
            throw new ServiceException("Updating of worker failed",e);
        }
    }

    @Override
    public void updateWorkerDto(Integer workerId, WorkerDto workerDto) {
        try {
            LOGGER.info(String.format("Updating of worker %d",workerId));
            Worker workerGet=workerDao.getById(workerId);
            workerGet.setLastName(workerDto.getLastName());
            workerGet.setFirstName(workerDto.getFirstName());
            workerGet.setPatronymic(workerDto.getPatronymic());
            workerGet.setData(workerDto.getData());
            workerDao.update(workerGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of worker failed",e);
            throw new ServiceException("Updating of worker failed",e);
        }
    }

    @Override
    public List<WorkerDto> getAll() {
        try{
            LOGGER.info(String.format("Getting of all workers"));
            List<Worker> workerList=workerDao.getAll();
            List<WorkerDto> workerDtoList=new ArrayList<>();
            for(Worker worker:workerList){
                WorkerDto workerDto=mapper.map(worker,WorkerDto.class);
                workerDtoList.add(workerDto);
            }
            return workerDtoList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all workers failed",e);
            throw new ServiceException("Getting of all workers failed",e);
        }
    }

    @Override
    public List<Worker> getAllUi() {
        try{
            LOGGER.info(String.format("Getting of all workers"));
            List<Worker> workerList=workerDao.getAll();
            return workerList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all workers failed",e);
            throw new ServiceException("Getting of all workers failed",e);
        }
    }

    @Override
    public void deleteWorker(Integer workerId) {
        try{
            LOGGER.info(String.format("Deleting of worker %d",workerId));
            Worker worker=workerDao.getById(workerId);
            workerDao.delete(worker);
        } catch (DaoException e) {
            LOGGER.warn("Deleting of worker failed",e);
            throw new ServiceException("Deleting of worker failed",e);
        }
    }

}
