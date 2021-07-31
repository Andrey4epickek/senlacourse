package com.library.ui.actions.book;

import com.library.facade.LibraryFacade;
import com.library.model.Book;
import com.library.model.dto.BookDto;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UpdateBook extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите id книги, которую необходимо обноваить");
            String bookIdString=bufferedReader.readLine();
            Integer bookIdInteger=Integer.parseInt(bookIdString);
            BookDto bookDto=libraryFacade.getBook(bookIdInteger);
            Book bookGet=mapper.map(bookDto,Book.class);
            System.out.println("Введите измененное  название");
            String bookTitle=bufferedReader.readLine();
            System.out.println("Введите измененную дату записи книги");
            String bookDataString=bufferedReader.readLine();
            LocalDate bookData=LocalDate.parse(bookDataString);
            System.out.println("Введите измененного автора книги");
            String bookAuthor=bufferedReader.readLine();
            System.out.println("Введите измененный год выпуска книнги");
            String bookGodString=bufferedReader.readLine();
            Integer bookGodInteger=Integer.parseInt(bookGodString);
            System.out.println("Введите измененный жанр книги");
            String bookGenre=bufferedReader.readLine();
            System.out.println("Введите измененное количество страниц в книге");
            String bookSumString=bufferedReader.readLine();
            Integer bookSumInteger=Integer.parseInt(bookSumString);
            bookGet.setTitle(bookTitle);
            bookGet.setData(bookData);
            bookGet.setAuthor(bookAuthor);
            bookGet.setGod(bookGodInteger);
            bookGet.setGenre(bookGenre);
            bookGet.setSum(bookSumInteger);
            libraryFacade.updateBook(bookIdInteger,bookGet);
            System.out.println(bookGet);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
