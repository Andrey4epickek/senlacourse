package com.library.ui.actions.reader;

import com.library.facade.LibraryFacade;
import com.library.model.Reader;
import com.library.model.dto.ReaderDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetReader extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id читателя для поиска");
            String readerIdString=bufferedReader.readLine();
            Integer readerIdInteger=Integer.parseInt(readerIdString);
            ReaderDto readerDto=libraryFacade.getReader(readerIdInteger);
            System.out.println(readerDto);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
