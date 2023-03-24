package hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.Bookstore.domain.Book;
import hh.Bookstore.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test  // testataan BookRepositoryn findByTitle()-metodin toimivuutta
    public void findByLastnameShouldReturnStudent() {
        List<Book> books = repository.findByTitle("Dune");
      
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Dune");
    }
    
    @Test // testataan BookRepositoryn save()-metodin toimivuutta
    public void createNewBook() {
    	Book book = new Book("Mickey Mouse House", "Disney", "4141242141", 1950, 20.00, null);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull(); 
    }
    
    @Test // testataan BookRepositoryn delete()-metodin toimivuutta
    public void deleteNewBook() {
    	Book book = repository.findById(1);
    	repository.delete(book);
    	assertThat(repository.findById(1)).isNull(); 
    }    

}