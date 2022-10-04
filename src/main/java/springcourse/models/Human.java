package springcourse.models;

import java.util.List;

public class Human {
    private int human_id;
    private String name;
    private int year_of_birth;

    public int getHuman_id() {
        return human_id;
    }

    public void setHuman_id(int human_id) {
        this.human_id = human_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year_of_birth;
    }

    public void setYear(int year) {
        this.year_of_birth = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Human() {}

    public Human(String name, int year, List<Book> books) {
        this.name = name;
        this.year_of_birth = year;
        this.books = books;
    }

    private List<Book> books;

}
