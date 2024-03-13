package exercise_4.exercise_4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import exercise_4.exercise_4.domain.Book;
import exercise_4.exercise_4.domain.BookRepository;
import exercise_4.exercise_4.domain.Category;
import exercise_4.exercise_4.domain.CategoryRepository;

//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Exercise4Application.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = crepository.findByName("Fiction");
        
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Fiction");
    }
    
    @Test
    public void createNewCategory() {
    	Category category = new Category("Kids");
    	crepository.save(category);

    	assertThat(category.getId()).isNotNull();
    }    

    @Test
    public void deleteNewCategory() {
        List<Category> categories = crepository.findByName("Non-Fiction");
        Category category = categories.get(0);
		crepository.delete(category);
		List<Category> newCategories = crepository.findByName("Non-Fiction");
		assertThat(newCategories).hasSize(0);
     }
}

