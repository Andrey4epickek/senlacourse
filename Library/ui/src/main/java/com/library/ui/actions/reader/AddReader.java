package com.library.ui.actions.reader;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AddReader extends AbstractAction implements IAction {
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
            String readerLastName=bufferedReader.readLine();
            System.out.println("Введите имя");
            String readerFirstName=bufferedReader.readLine();
            System.out.println("Введите отчество");
            String readerPatronymic=bufferedReader.readLine();
            System.out.println("Введите возраст");
            String readerAgeString=bufferedReader.readLine();
            Integer readerAgeInteger=Integer.parseInt(readerAgeString);
            System.out.println("Введите дату регистрации");
            String readerDataString=bufferedReader.readLine();
            LocalDate readerData = LocalDate.parse(readerDataString);
            Reader reader=libraryFacade.addReader(readerLastName,readerFirstName,readerPatronymic,readerAgeInteger,readerData);
            System.out.println(reader);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
