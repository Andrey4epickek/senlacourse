package com.library.ui.actions.worker;

import com.library.facade.LibraryFacade;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DeleteWorker extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id работника, которого надо удалить");
            String workerIdString=bufferedReader.readLine();
            Integer workerIdInteger=Integer.parseInt(workerIdString);
            libraryFacade.deleteWorker(workerIdInteger);
            System.out.println("Удален работник с id = "+workerIdInteger);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
