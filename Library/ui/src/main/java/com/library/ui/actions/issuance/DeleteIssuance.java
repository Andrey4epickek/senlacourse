package com.library.ui.actions.issuance;

import com.library.facade.LibraryFacade;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DeleteIssuance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id выдачи, которую надо удалить");
            String issuanceIdString=bufferedReader.readLine();
            Integer issuanceIdInteger=Integer.parseInt(issuanceIdString);
            libraryFacade.deleteIssuance(issuanceIdInteger);
            System.out.println("Удалена выдача с id = "+issuanceIdInteger);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
