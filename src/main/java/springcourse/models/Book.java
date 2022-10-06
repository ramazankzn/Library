package springcourse.models;

import javax.validation.constraints.*;

public class Book {
    private int book_id;

    private Integer owner_id;
    @NotEmpty(message = "Поле название не может быть пустым")
    private String name;
    @NotEmpty(message = "Поле автор не может быть пустым")
    private String author;
    @NotNull(message = "Поле год не может быть пустым")
    @Min(value = 0, message = "Год не может быть отрицательным")
    @Max(value = 2022, message = "Год не может быть больше 2022")
    private int year_of_production;


    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public Book() {

    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public Book(int book_id, int owner_id, String name, String author, int year_of_production) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year_of_production = year_of_production;
        this.owner_id = owner_id;
    }

    public Book(String name, String author, int year_of_production) {
        this.name = name;
        this.author = author;
        this.year_of_production = year_of_production;
    }
}
