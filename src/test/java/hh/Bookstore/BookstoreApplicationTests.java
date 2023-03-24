package hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.Bookstore.web.*;



//@RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Test
	public void contextLoads() {
		assertThat(bookController).isNotNull();
	}
}