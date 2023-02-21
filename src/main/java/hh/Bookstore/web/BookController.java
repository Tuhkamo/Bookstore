package hh.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.Bookstore.domain.Book;
import hh.Bookstore.domain.BookRepository;
import hh.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

//	@GetMapping("/index")
//	public String getBooks(Model model) {
//		List<Book> booklist = new ArrayList<Book>();
//		model.addAttribute("booklist", booklist);
//		return "index";
//	}
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
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
        return "redirect:booklist";
    }
	
//	@PostMapping("/overwrite/{id}")
//    public String overwrite(@PathVariable("id") Long id, Book book){
//        repository.deleteById(id);
//        repository.save(book);
//        return "redirect:../booklist";
//   }
	
	@GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	brepository.deleteById(id);
        return "redirect:../booklist";
    }
	
	@GetMapping("/edit/{book}")
    public String editBook(@PathVariable("book") Book book, Model model) {
    	model.addAttribute(book);
        return "editbook";
    }
	
	
}
