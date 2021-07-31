package com.library.ui.actions.reader;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetAllReaders extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            List<Reader> readers=libraryFacade.getAllReaders();
            System.out.println(readers);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
