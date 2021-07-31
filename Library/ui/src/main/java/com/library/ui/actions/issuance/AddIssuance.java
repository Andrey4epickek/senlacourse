package com.library.ui.actions.issuance;

import com.library.facade.LibraryFacade;
import com.library.model.Book;
import com.library.model.Issuance;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.model.dto.BookDto;
import com.library.model.dto.IssuanceDto;
import com.library.model.dto.ReaderDto;
import com.library.model.dto.WorkerDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AddIssuance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id рабочего");
            String workerIdString=bufferedReader.readLine();
            Integer workerIdInteger=Integer.parseInt(workerIdString);
            WorkerDto workerDto=libraryFacade.getWorker(workerIdInteger);
            System.out.println("Введите id читателя");
            String readerIdString=bufferedReader.readLine();
            Integer readerIdInteger=Integer.parseInt(readerIdString);
            ReaderDto readerDto=libraryFacade.getReader(readerIdInteger);
            System.out.println("Введите id книги");
            String bookIdString=bufferedReader.readLine();
            Integer bookIdInteger=Integer.parseInt(bookIdString);
            BookDto bookDto=libraryFacade.getBook(bookIdInteger);
            System.out.println("Введите количество книг");
            String bookSumString=bufferedReader.readLine();
            Integer bookSumInteger=Integer.parseInt(bookSumString);
            System.out.println("Введите число дней выдачи");
            String timeString=bufferedReader.readLine();
            Integer timeInteger=Integer.parseInt(timeString);
            System.out.println("Введите дату выдачи книг");
            String dataString=bufferedReader.readLine();
            LocalDate data = LocalDate.parse(dataString);
            Issuance issuance=libraryFacade.addIssuance(workerDto,readerDto,bookDto,bookSumInteger,timeInteger,data);
            System.out.println(issuance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
