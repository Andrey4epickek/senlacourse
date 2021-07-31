package com.library.ui.actions.acceptance;

import com.library.facade.LibraryFacade;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DeleteAcceptance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id возврата, который надо удалить");
            String acceptanceIdString=bufferedReader.readLine();
            Integer acceptanceIdInteger=Integer.parseInt(acceptanceIdString);
            libraryFacade.deleteAcceptance(acceptanceIdInteger);
            System.out.println("Удален возврат с id = "+acceptanceIdInteger);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
