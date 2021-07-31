package com.library.ui.actions.acceptance;

import com.library.facade.LibraryFacade;
import com.library.model.Acceptance;
import com.library.model.Issuance;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetAllAcceptance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            List<Acceptance> acceptance=libraryFacade.getAllAcceptance();
            System.out.println(acceptance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
