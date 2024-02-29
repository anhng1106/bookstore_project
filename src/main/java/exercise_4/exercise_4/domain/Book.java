package exercise_4.exercise_4.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long bookId;
    private String title;
    private String author;
    private int year;
    private String isbn;
    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

    public Book() {}

    public Book(String author, String title, String isbn, int year, Category category) {
        super();
        this.author = author;
        this.title  = title;
        this.isbn = isbn;
        this.year = year;
        this.category = category;
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return this.bookId;
    }
    public void setId(Long bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getIsbn() {
        return this.isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    // public double getPrice() {
    //     return this.price;
    // }
    // public void setPrice(double price) {
    //     this.price = price;
    // }
    public String getAuthor() {
        return this.author;
    }

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    @Override
    public String toString()
    {
        if (this.category != null)
        return "Book [id=" + bookId + ", author=" + author + ", title=" + title + ", isbn=" + isbn
                + " category =" + this.getCategory() + "]";
    else
    return "Book [id=" + bookId + ", author=" + author + ", title=" + title + ", isbn=" + isbn + "]";
    }  
    }

