package com.library.service;

import com.hotel.api.dao.IAcceptanceDao;
import com.hotel.api.dao.IIssuanceDao;
import com.hotel.api.service.IAcceptanceService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.*;
import com.library.model.dto.AcceptanceDto;
import com.library.model.dto.BookDto;
import com.library.model.dto.ReaderDto;
import com.library.model.dto.WorkerDto;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Service
@RequiredArgsConstructor
@Transactional
public class AcceptanceService implements IAcceptanceService {

    private static final Logger LOGGER= LogManager.getLogger(AcceptanceService.class.getName());
    private final IAcceptanceDao acceptanceDao;
    private final ModelMapper mapper;

    @Override
    public Acceptance addAcceptance(WorkerDto workerDto, ReaderDto readerDto, BookDto bookDto, Integer sum, LocalDate data) {
        try {
            LOGGER.info(String.format("Adding of acceptance "));
            Acceptance acceptance=new Acceptance();
            Worker worker=mapper.map(workerDto,Worker.class);
            acceptance.setWorker(worker);
            Reader reader=mapper.map(readerDto,Reader.class);
            acceptance.setReader(reader);
            Book book=mapper.map(bookDto,Book.class);
            acceptance.setBook(book);
            acceptance.setSum(sum);
            acceptance.setData(data);
            acceptanceDao.save(acceptance);
            return acceptance;
        } catch (DaoException e) {
            LOGGER.warn("Adding of acceptance failed",e);
            throw new ServiceException("Adding of acceptance failed",e);
        }
    }

    @Override
    public AcceptanceDto getById(Integer acceptanceId) {
        try {
            LOGGER.info(String.format("Getting of acceptance %d",acceptanceId));
            Acceptance acceptance=acceptanceDao.getById(acceptanceId);
            return mapper.map(acceptance,AcceptanceDto.class);
        }catch (DaoException e){
            LOGGER.warn("Getting acceptance failed",e);
            throw new ServiceException("Getting acceptance failed",e);
        }
    }

    @Override
    public Acceptance getByIdUi(Integer acceptanceId) {
        try {
            LOGGER.info(String.format("Getting of acceptance %d",acceptanceId));
            Acceptance acceptance=acceptanceDao.getById(acceptanceId);
            return acceptance;
        }catch (DaoException e){
            LOGGER.warn("Getting acceptance failed",e);
            throw new ServiceException("Getting acceptance failed",e);
        }
    }

    @Override
    public void updateAcceptance(Integer acceptanceId, Acceptance acceptance) {
        try {
            LOGGER.info(String.format("Updating of acceptance %d",acceptanceId));
            Acceptance acceptanceGet=acceptanceDao.getById(acceptanceId);
            acceptanceGet.setWorker(acceptance.getWorker());
            acceptanceGet.setReader(acceptance.getReader());
            acceptanceGet.setBook(acceptance.getBook());
            acceptanceGet.setSum(acceptance.getSum());
            acceptanceGet.setData(acceptance.getData());
            acceptanceDao.update(acceptanceGet);
        }catch (DaoException e){
            LOGGER.warn("Updating acceptance failed",e);
            throw new ServiceException("Updating acceptance failed",e);
        }
    }

    @Override
    public void updateAcceptanceDto(Integer acceptanceId, AcceptanceDto acceptanceDto) {
        try {
            LOGGER.info(String.format("Updating of acceptance %d",acceptanceId));
            Acceptance acceptanceGet=acceptanceDao.getById(acceptanceId);
            Worker worker=mapper.map(acceptanceDto.getWorkerDto(),Worker.class);
            acceptanceGet.setWorker(worker);
            Reader reader=mapper.map(acceptanceDto.getReaderDto(),Reader.class);
            acceptanceGet.setReader(reader);
            Book book=mapper.map(acceptanceDto.getBookDto(),Book.class);
            acceptanceGet.setBook(book);
            acceptanceGet.setSum(acceptanceDto.getSum());
            acceptanceGet.setData(acceptanceDto.getData());
            acceptanceDao.update(acceptanceGet);
        }catch (DaoException e){
            LOGGER.warn("Updating acceptance failed",e);
            throw new ServiceException("Updating acceptance failed",e);
        }
    }

    @Override
    public List<AcceptanceDto> getAll() {
        try{
            LOGGER.info(String.format("Getting of all acceptances"));
            List<Acceptance> acceptanceList=acceptanceDao.getAll();
            List<AcceptanceDto> acceptanceDtoList=new ArrayList<>();
            for(Acceptance acceptance:acceptanceList){
                AcceptanceDto acceptanceDto=mapper.map(acceptance,AcceptanceDto.class);
                acceptanceDtoList.add(acceptanceDto);
            }
             return acceptanceDtoList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all acceptance failed",e);
            throw new ServiceException("Getting of all acceptance failed",e);
        }
    }

    @Override
    public List<Acceptance> getAllUi() {
        try{
            LOGGER.info(String.format("Getting of all acceptance"));
            List<Acceptance> acceptanceList=acceptanceDao.getAll();
            return acceptanceList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all acceptance failed",e);
            throw new ServiceException("Getting of all acceptance failed",e);
        }
    }

    @Override
    public void deleteAcceptance(Integer acceptanceId) {
        try{
            LOGGER.info(String.format("Deleting of acceptance %d",acceptanceId));
            Acceptance acceptance=acceptanceDao.getById(acceptanceId);
            acceptanceDao.delete(acceptance);
        } catch (DaoException e) {
            LOGGER.warn("Deleting of acceptance failed",e);
            throw new ServiceException("Deleting of acceptance failed",e);
        }
    }
}
