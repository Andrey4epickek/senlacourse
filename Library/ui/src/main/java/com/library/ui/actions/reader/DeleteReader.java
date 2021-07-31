package com.library.ui.actions.reader;

import com.library.facade.LibraryFacade;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DeleteReader extends AbstractAction implements IAction {
    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id читателя, которого надо удалить");
            String readerIdString=bufferedReader.readLine();
            Integer readerIdInteger=Integer.parseInt(readerIdString);
            libraryFacade.deleteReader(readerIdInteger);
            System.out.println("Удален пользователь с id = "+readerIdInteger);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
