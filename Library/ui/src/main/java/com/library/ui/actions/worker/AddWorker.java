package com.library.ui.actions.worker;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AddWorker extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите фамилию");
            String workerLastName=bufferedReader.readLine();
            System.out.println("Введите имя");
            String workerFirstName=bufferedReader.readLine();
            System.out.println("Введите отчество");
            String workerPatronymic=bufferedReader.readLine();
            System.out.println("Введите дату регистрации");
            String workerDataString=bufferedReader.readLine();
            LocalDate workerData = LocalDate.parse(workerDataString);
            Worker worker=libraryFacade.addWorker(workerLastName,workerFirstName,workerPatronymic,workerData);
            System.out.println(worker);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
