package com.library.ui.actions.reader;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.model.dto.ReaderDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UpdateReader extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id читателя, которого надо обновить");
            String readerIdString=bufferedReader.readLine();
            Integer readerIdInteger=Integer.parseInt(readerIdString);
            ReaderDto readerDto=libraryFacade.getReader(readerIdInteger);
            Reader readerGet=mapper.map(readerDto,Reader.class);
            System.out.println("Введите измененную фамилию");
            String readerLastName=bufferedReader.readLine();
            System.out.println("Введите измененное имя");
            String readerFirstName=bufferedReader.readLine();
            System.out.println("Введите измененное отчество");
            String readerPatronymic=bufferedReader.readLine();
            System.out.println("Введите измененный возраст");
            String readerAgeString=bufferedReader.readLine();
            Integer readerAgeInteger=Integer.parseInt(readerAgeString);
            System.out.println("Введите измененную дату регистрации");
            String readerDataString=bufferedReader.readLine();
            LocalDate readerData = LocalDate.parse(readerDataString);
            readerGet.setLastName(readerLastName);
            readerGet.setFirstName(readerFirstName);
            readerGet.setPatronymic(readerPatronymic);
            readerGet.setAge(readerAgeInteger);
            readerGet.setData(readerData);
            libraryFacade.updateReader(readerIdInteger,readerGet);
            System.out.println(readerGet);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
