package hh.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.Bookstore.domain.Book;
import hh.Bookstore.domain.BookRepository;
import hh.Bookstore.domain.Category;
import hh.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}
	
	@GetMapping("/booklist/{id}")
	public @ResponseBody Optional<Book> findBookRestById(@PathVariable("id") Long bookId) {
		return brepository.findById(bookId);
	}
	
	@GetMapping("/add")
	public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
	
	
	@PostMapping("/save")
    public String save(Book book){
        brepository.save(book);
        return "redirect:/booklist";
    }
	
	@PostMapping("/books")
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return brepository.save(book);
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	brepository.deleteById(id);
        return "redirect:../booklist";
    }
	
	@GetMapping("/edit/{book}")
    public String editBook(@PathVariable("book") Book book, Model model) {
    	model.addAttribute(book);
    	model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
		
	
}
