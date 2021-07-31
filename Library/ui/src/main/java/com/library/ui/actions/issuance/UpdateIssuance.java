package com.library.ui.actions.issuance;

import com.library.facade.LibraryFacade;
import com.library.model.*;
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
public class UpdateIssuance extends AbstractAction implements IAction {

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
            Issuance issuanceGet=libraryFacade.getIssuance(issuanceIdInteger);
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
            System.out.println("Введите измененное число дней выдачи");
            String timeString=bufferedReader.readLine();
            Integer timeInteger=Integer.parseInt(timeString);
            System.out.println("Введите измененную дату выдачи книг");
            String dataString=bufferedReader.readLine();
            LocalDate data = LocalDate.parse(dataString);
            issuanceGet.setWorker(worker);
            issuanceGet.setReader(reader);
            issuanceGet.setBook(book);
            issuanceGet.setSum(bookSumInteger);
            issuanceGet.setTime(timeInteger);
            issuanceGet.setData(data);
            libraryFacade.updateIssuance(issuanceIdInteger,issuanceGet);
            System.out.println(issuanceGet);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
