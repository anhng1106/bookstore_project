package exercise_4.exercise_4.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exercise_4.exercise_4.domain.Book;
import exercise_4.exercise_4.domain.BookRepository;
import exercise_4.exercise_4.domain.CategoryRepository;

@Controller
public class BookController {

     // Inject the BookRepository
     @Autowired
     private BookRepository bookRepository;

     @Autowired
	private CategoryRepository categoryRepository; 

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
    //show all books
   @RequestMapping(value= "/booklist")
    public String bookList(Model model) {
        model.addAttribute("booklist", bookRepository.findAll());
        return "booklist";
    }

    // RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    

	// RESTful service to get student by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }       

    //Add new book
    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("category", categoryRepository.findAll());
        return "addbook";
    }
    
    //Save new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:booklist"; 
    }

    //Delete book
    // Demo here how to use @PreAuthorize annotation
    //@PreAuthorize("hasAuthority('ADMIN')") 
    // a) try to delete book (with USER ROLE)
    // b) @PreAuthorize delete only for ADMIN ROLE
    // c) try to delete book again  (with USER ROLE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../booklist"; 
    }

    // Edit book
@SuppressWarnings("null")
@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
public String editBook(@PathVariable("id") Long bookId, Model model) {
model.addAttribute("book", bookRepository.findById(bookId));
model.addAttribute("category", categoryRepository.findAll());
    return "editbook";
}
}
