package com.library.service;

import com.hotel.api.dao.IIssuanceDao;
import com.hotel.api.service.IIssuanceService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.*;
import com.library.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IssuanceService implements IIssuanceService {

    private static final Logger LOGGER= LogManager.getLogger(IssuanceService.class.getName());
    private final IIssuanceDao issuanceDao;
    private final ModelMapper mapper;

    @Override
    public Issuance addIssuance(WorkerDto workerDto, ReaderDto readerDto, BookDto bookDto, Integer sum, Integer time, LocalDate data) {
        try {
            LOGGER.info(String.format("Adding of issuance "));
            Issuance issuance=new Issuance();
            Worker worker=mapper.map(workerDto,Worker.class);
            issuance.setWorker(worker);
            Reader reader=mapper.map(readerDto,Reader.class);
            issuance.setReader(reader);
            Book book=mapper.map(bookDto,Book.class);
            issuance.setBook(book);
            issuance.setSum(sum);
            issuance.setTime(time);
            issuance.setData(data);
            issuanceDao.save(issuance);
            return issuance;
        } catch (DaoException e) {
            LOGGER.warn("Adding of issuance failed",e);
            throw new ServiceException("Adding of issuance failed",e);
        }
    }

    @Override
    public IssuanceDto getById(Integer issuanceId) {
        try {
            LOGGER.info(String.format("Getting of issuance %d",issuanceId));
            Issuance issuance=issuanceDao.getById(issuanceId);
            return mapper.map(issuance, IssuanceDto.class);
        }catch (DaoException e){
            LOGGER.warn("Getting issuance failed",e);
            throw new ServiceException("Getting issuance failed",e);
        }
    }

    @Override
    public Issuance getByIdUi(Integer issuanceId) {
        try {
            LOGGER.info(String.format("Getting of issuance %d",issuanceId));
            Issuance issuance=issuanceDao.getById(issuanceId);
            return issuance;
        }catch (DaoException e){
            LOGGER.warn("Getting issuance failed",e);
            throw new ServiceException("Getting issuance failed",e);
        }
    }

    @Override
    public void updateIssuance(Integer issuanceId, Issuance issuance) {
        try {
            LOGGER.info(String.format("Updating of issuance %d",issuanceId));
            Issuance issuanceGet=issuanceDao.getById(issuanceId);
            issuanceGet.setWorker(issuance.getWorker());
            issuanceGet.setReader(issuance.getReader());
            issuanceGet.setBook(issuance.getBook());
            issuanceGet.setSum(issuance.getSum());
            issuanceGet.setTime(issuance.getTime());
            issuanceGet.setData(issuance.getData());
            issuanceDao.update(issuanceGet);
        }catch (DaoException e){
            LOGGER.warn("Updating issuance failed",e);
            throw new ServiceException("Updating issuance failed",e);
        }
    }

    @Override
    public void updateIssuanceDto(Integer issuanceId, IssuanceDto issuanceDto) {
        try {
            LOGGER.info(String.format("Updating of issuance %d",issuanceId));
            Issuance issuanceGet=issuanceDao.getById(issuanceId);
            Worker worker=mapper.map(issuanceDto.getWorkerDto(),Worker.class);
            issuanceGet.setWorker(worker);
            Reader reader=mapper.map(issuanceDto.getReaderDto(),Reader.class);
            issuanceGet.setReader(reader);
            Book book=mapper.map(issuanceDto.getBookDto(),Book.class);
            issuanceGet.setBook(book);
            issuanceGet.setSum(issuanceDto.getSum());
            issuanceGet.setTime(issuanceDto.getTime());
            issuanceGet.setData(issuanceDto.getData());
            issuanceDao.update(issuanceGet);
        }catch (DaoException e){
            LOGGER.warn("Updating issuance failed",e);
            throw new ServiceException("Updating issuance failed",e);
        }
    }

    @Override
    public List<IssuanceDto> getAll() {
        try{
            LOGGER.info(String.format("Getting of all issuance"));
            List<Issuance> issuanceList=issuanceDao.getAll();
            List<IssuanceDto> issuanceDtoList=new ArrayList<>();
            for(Issuance issuance:issuanceList){
                IssuanceDto issuanceDto=mapper.map(issuance,IssuanceDto.class);
                issuanceDtoList.add(issuanceDto);
            }
            return issuanceDtoList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all issuance failed",e);
            throw new ServiceException("Getting of all issuance failed",e);
        }
    }

    @Override
    public List<Issuance> getAllUi() {
        try{
            LOGGER.info(String.format("Getting of all issuance"));
            List<Issuance> issuanceList=issuanceDao.getAll();
            return issuanceList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all issuance failed",e);
            throw new ServiceException("Getting of all issuance failed",e);
        }
    }

    @Override
    public void deleteIssuance(Integer issuanceId) {
        try{
            LOGGER.info(String.format("Deleting of issuance %d",issuanceId));
            Issuance issuance=issuanceDao.getById(issuanceId);
            issuanceDao.delete(issuance);
        } catch (DaoException e) {
            LOGGER.warn("Deleting of issuance failed",e);
            throw new ServiceException("Deleting of issuance failed",e);
        }
    }
}
