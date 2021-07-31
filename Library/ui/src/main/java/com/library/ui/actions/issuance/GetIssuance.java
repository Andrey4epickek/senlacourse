package com.library.ui.actions.issuance;

import com.library.facade.LibraryFacade;
import com.library.model.Book;
import com.library.model.Issuance;
import com.library.model.dto.IssuanceDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetIssuance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id выдачи для поиска");
            String issuanceIdString=bufferedReader.readLine();
            Integer issuanceIdInteger=Integer.parseInt(issuanceIdString);
            Issuance issuance=libraryFacade.getIssuance(issuanceIdInteger);
            System.out.println(issuance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
