package com.library.service;

import com.hotel.api.dao.IReaderDao;
import com.hotel.api.service.IReaderService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.Reader;
import com.library.model.dto.ReaderDto;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReaderService implements IReaderService {

    private static final Logger LOGGER= LogManager.getLogger(ReaderService.class.getName());

    private final IReaderDao readerDao;
    private final ModelMapper mapper;

    @Override
    public Reader addReader(String lastName, String firstName, String patronymic, Integer age, LocalDate data) {
        try {
            LOGGER.info(String.format("Adding of reader %s,age %d",lastName,age));
            Reader reader=new Reader();
            reader.setLastName(lastName);
            reader.setFirstName(firstName);
            reader.setPatronymic(patronymic);
            reader.setAge(age);
            reader.setData(data);
            readerDao.save(reader);
            return reader;
        } catch (DaoException e) {
            LOGGER.warn("Adding of reader failed",e);
            throw new ServiceException("Adding of reader failed",e);
        }
    }

    @Override
    public ReaderDto getById(Integer readerId) {
        try {
        LOGGER.info(String.format("Getting of reader %d",readerId));
        Reader reader=readerDao.getById(readerId);
        return mapper.map(reader, ReaderDto.class);
        } catch (DaoException e) {
        LOGGER.warn("Getting of reader failed",e);
        throw new ServiceException("Getting of reader failed",e);
        }
    }

    @Override
    public Reader getByIdUi(Integer readerId) {
        try {
            LOGGER.info(String.format("Getting of reader %d",readerId));
            Reader reader=readerDao.getById(readerId);
            return reader;
        } catch (DaoException e) {
            LOGGER.warn("Getting of reader failed",e);
            throw new ServiceException("Getting of reader failed",e);
        }
    }

    @Override
    public void updateReader(Integer readerId, Reader reader) {
        try {
            LOGGER.info(String.format("Updating of reader %d",readerId));
            Reader readerGet=readerDao.getById(readerId);
            readerGet.setLastName(reader.getLastName());
            readerGet.setFirstName(reader.getFirstName());
            readerGet.setPatronymic(reader.getPatronymic());
            readerGet.setAge(reader.getAge());
            readerGet.setData(reader.getData());
            readerDao.update(readerGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of reader failed",e);
            throw new ServiceException("Updating of reader failed",e);
        }
    }

    @Override
    public void updateReaderDto(Integer readerId, ReaderDto readerDto) {
        try {
            LOGGER.info(String.format("Updating of reader %d",readerId));
            Reader readerGet=readerDao.getById(readerId);
            readerGet.setLastName(readerDto.getLastName());
            readerGet.setFirstName(readerDto.getFirstName());
            readerGet.setPatronymic(readerDto.getPatronymic());
            readerGet.setAge(readerDto.getAge());
            readerGet.setData(readerDto.getData());
            readerDao.update(readerGet);
        } catch (DaoException e) {
            LOGGER.warn("Updating of reader failed",e);
            throw new ServiceException("Updating of reader failed",e);
        }
    }

    @Override
    public List<ReaderDto> getAll() {
        try{
            LOGGER.info(String.format("Getting of all readers"));
            List<Reader> readerList=readerDao.getAll();
            readerList.sort(((o1, o2) -> o1.getAge()- o2.getAge()));
            List<ReaderDto> readerDtoList=new ArrayList<>();
            for(Reader reader:readerList){
                ReaderDto readerDto=mapper.map(reader,ReaderDto.class);
                readerDtoList.add(readerDto);
            }
            return readerDtoList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all readers failed",e);
            throw new ServiceException("Getting of all readers failed",e);
        }
    }

    @Override
    public List<Reader> getAllUi() {
        try{
            LOGGER.info(String.format("Getting of all readers"));
            List<Reader> readerList=readerDao.getAll();
            return readerList;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all readers failed",e);
            throw new ServiceException("Getting of all readers failed",e);
        }
    }

    @Override
    public void deleteReader(Integer readerId) {
        try{
            LOGGER.info(String.format("Deleting of reader %d",readerId));
            Reader reader=readerDao.getById(readerId);
            readerDao.delete(reader);
        } catch (DaoException e) {
            LOGGER.warn("Deleting of reader failed",e);
            throw new ServiceException("Deleting of reader failed",e);
        }
    }
}
