package com.library.ui.actions.worker;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.model.dto.ReaderDto;
import com.library.model.dto.WorkerDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UpdateWorker extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id работника, которого надо обновить");
            String workerIdString=bufferedReader.readLine();
            Integer workerIdInteger=Integer.parseInt(workerIdString);
            WorkerDto workerDto=libraryFacade.getWorker(workerIdInteger);
            Worker workerGet=mapper.map(workerDto,Worker.class);
            System.out.println("Введите измененную фамилию");
            String workerLastName=bufferedReader.readLine();
            System.out.println("Введите измененное имя");
            String workerFirstName=bufferedReader.readLine();
            System.out.println("Введите измененное отчество");
            String workerPatronymic=bufferedReader.readLine();
            System.out.println("Введите измененную дату регистрации");
            String workerDataString=bufferedReader.readLine();
            LocalDate workerData = LocalDate.parse(workerDataString);
            workerGet.setLastName(workerLastName);
            workerGet.setFirstName(workerFirstName);
            workerGet.setPatronymic(workerPatronymic);
            workerGet.setData(workerData);
            libraryFacade.updateWorker(workerIdInteger,workerGet);
            System.out.println(workerGet);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
