package com.library.ui.actions.worker;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.model.dto.WorkerDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetWorker extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id работника для поиска");
            String workerIdString=bufferedReader.readLine();
            Integer workerIdInteger=Integer.parseInt(workerIdString);
            WorkerDto workerDto=libraryFacade.getWorker(workerIdInteger);
            System.out.println(workerDto);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
