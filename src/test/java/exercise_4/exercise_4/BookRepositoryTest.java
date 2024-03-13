package exercise_4.exercise_4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import exercise_4.exercise_4.domain.Book;
import exercise_4.exercise_4.domain.BookRepository;
import exercise_4.exercise_4.domain.Category;
import exercise_4.exercise_4.domain.CategoryRepository;

//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Exercise4Application.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = brepository.findByAuthor("John Green");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("John Green");
    }
    
    @Test
    public void createNewBook() {
    	Category category = new Category("Kids");
    	crepository.save(category);
    	Book book = new Book("C.S.Lewis", "The Lion, the Witch and the Wardrobe", "B001KRMT5G", 1950, category);
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteNewBook() {
        List<Book> books = brepository.findByAuthor("John Green");
        Book book = books.get(0);
		brepository.delete(book);
		List<Book> newBooks = brepository.findByAuthor("John Green");
		assertThat(newBooks).hasSize(0);
     }
}
