package hh.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.Bookstore.domain.Book;
import hh.Bookstore.domain.BookRepository;
import hh.Bookstore.domain.Category;
import hh.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			
			log.info("Saving sample categories");
			crepository.save(new Category("Sci-Fi"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Fantasy"));
			
			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("Saving sample books");
			brepository.save(new Book("Fight Club", "Chuck Palahniuk", "197281726-21", 1996, 12.00)); 
			brepository.save(new Book("Game of Thrones", "George R. R. Martin", "174107842-51", 1996, 20.00));
			brepository.save(new Book("Dune", "Frank Herbert", "1251521-31", 1965, 35.00));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
	
}
