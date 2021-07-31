package com.library.ui.actions.book;

import com.library.facade.LibraryFacade;
import com.library.model.Book;
import com.library.ui.actions.AbstractAction;
import com.library.ui.actions.IAction;
import com.library.ui.actions.reader.AddReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AddBook extends AbstractAction implements IAction {

    protected Logger LOGGER=Logger.getLogger(AddReader.class.getName());
    private LibraryFacade libraryFacade;

    @Autowired
    public void setLibraryFacade(LibraryFacade libraryFacade){
        this.libraryFacade=libraryFacade;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Введите название книги");
            String bookTitle=bufferedReader.readLine();
            System.out.println("Введите дату записи книги");
            String bookDataString=bufferedReader.readLine();
            LocalDate bookData=LocalDate.parse(bookDataString);
            System.out.println("Введите автора книги");
            String bookAuthor=bufferedReader.readLine();
            System.out.println("Введите год выпуска книнги");
            String bookGodString=bufferedReader.readLine();
            Integer bookGodInteger=Integer.parseInt(bookGodString);
            System.out.println("Введите жанр книги");
            String bookGenre=bufferedReader.readLine();
            System.out.println("Введите количество страниц в книге");
            String bookSumString=bufferedReader.readLine();
            Integer bookSumInteger=Integer.parseInt(bookSumString);
            Book book =libraryFacade.addBook(bookTitle,bookData,bookAuthor,bookGodInteger,bookGenre,bookSumInteger);
            System.out.println(book);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getLocalizedMessage(),e);
        }
    }
}
