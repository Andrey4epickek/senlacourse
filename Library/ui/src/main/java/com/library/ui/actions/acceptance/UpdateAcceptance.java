package com.library.ui.actions.acceptance;

import com.library.facade.LibraryFacade;
import com.library.model.Acceptance;
import com.library.model.Book;
import com.library.model.Reader;
import com.library.model.Worker;
import com.library.model.dto.BookDto;
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
public class UpdateAcceptance extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id возврата для поиска");
            String acceptanceIdString=bufferedReader.readLine();
            Integer acceptanceIdInteger=Integer.parseInt(acceptanceIdString);
            Acceptance acceptanceGet=libraryFacade.getAcceptance(acceptanceIdInteger);
            System.out.println("Введите измененное id рабочего");
            String workerIdString=bufferedReader.readLine();
            Integer workerIdInteger=Integer.parseInt(workerIdString);
            WorkerDto workerDto=libraryFacade.getWorker(workerIdInteger);
            Worker worker=mapper.map(workerDto,Worker.class);
            System.out.println("Введите измененное id читателя");
            String readerIdString=bufferedReader.readLine();
            Integer readerIdInteger=Integer.parseInt(readerIdString);
            ReaderDto readerDto=libraryFacade.getReader(readerIdInteger);
            Reader reader=mapper.map(readerDto,Reader.class);
            System.out.println("Введите измененное id книги");
            String bookIdString=bufferedReader.readLine();
            Integer bookIdInteger=Integer.parseInt(bookIdString);
            BookDto bookDto=libraryFacade.getBook(bookIdInteger);
            Book book=mapper.map(bookDto,Book.class);
            System.out.println("Введите измененное количество книг");
            String bookSumString=bufferedReader.readLine();
            Integer bookSumInteger=Integer.parseInt(bookSumString);
            System.out.println("Введите измененную дату возврата книг");
            String dataString=bufferedReader.readLine();
            LocalDate data = LocalDate.parse(dataString);
            acceptanceGet.setWorker(worker);
            acceptanceGet.setReader(reader);
            acceptanceGet.setBook(book);
            acceptanceGet.setSum(bookSumInteger);
            acceptanceGet.setData(data);
            libraryFacade.updateAcceptance(acceptanceIdInteger,acceptanceGet);
            System.out.println(acceptanceGet);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
