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
import hh.Bookstore.domain.User;
import hh.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {

			log.info("Saving sample categories");
			Category categorySF = new Category("Sci-Fi");
			crepository.save(categorySF);
			Category categoryD = new Category("Drama");
			crepository.save(categoryD);
			Category categoryF = new Category("Fantasy");
			crepository.save(categoryF);

			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Saving sample books");
			brepository.save(new Book("Fight Club", "Chuck Palahniuk", "197281726-21", 1996, 12.00, categoryD));
			brepository.save(new Book("Game of Thrones", "George R. R. Martin", "174107842-51", 1996, 20.00, categoryF));
			brepository.save(new Book("Dune", "Frank Herbert", "1251521-31", 1965, 35.00, categorySF));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$upWlr2CrCHjaeFW9Y1Sfo.pJixT0.twrR8lCT5JNKDhnNcYETHd0.", "USER");
			User user2 = new User("admin", "$2a$10$BLIn0lduNSlBQmmLKypjj.y9Edo23JsmLX.XULxYPa1inuKAGhXc6", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
