package com.library.dao;

import com.hotel.api.dao.IWorkerDao;
import com.library.model.Worker;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerDao extends AbstractDao<Worker> implements IWorkerDao {
    @Override
    protected Class<Worker> getClazz() {
        return Worker.class;
    }
}
