package com.library.ui.actions.issuance;

import com.library.facade.LibraryFacade;
import com.library.model.Issuance;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class GetBookIssuanceHistory extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id книги для поиска");
            String bookIdString=bufferedReader.readLine();
            Integer bookIdInteger=Integer.parseInt(bookIdString);
            List<Issuance> issuance=libraryFacade.getAllIssuance().stream().filter(c->c.getBook().getId().equals(bookIdInteger)).collect(Collectors.toList());
            System.out.println(issuance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
