package exercise_4.exercise_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import exercise_4.exercise_4.domain.Book;
import exercise_4.exercise_4.domain.BookRepository;
import exercise_4.exercise_4.domain.Category;
import exercise_4.exercise_4.domain.CategoryRepository;

@SpringBootApplication
public class Exercise4Application {
	private static final Logger log = LoggerFactory.getLogger(Exercise4Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Exercise4Application.class, args);
	}


@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			Category category1 = new Category("Fiction");
			Category category2 = new Category("Non-Fiction");
			Category category3 = new Category("Science Fiction");
			
			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);
			
			brepository.save(new Book("Ernest Hermingway", "A Farewell to Arms", "1232323-21", 1929, category1));
			brepository.save(new Book("George Orwell", "Animal Farm", "22122343-5", 1945, category2));	
			
			log.info("fetch all students");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
