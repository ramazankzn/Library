package springcourse.models;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


public class Human {

    private int human_id;
    @NotEmpty(message = "Поле имя не может быть пустым")
    @Size(min = 3, message = "Имя слишком короткое")
    @Size(max = 100, message = "Имя слишком длинное")
    private String name;
    @NotNull(message = "Поле год рождения не может быть пустым")
    @Min(value = 1900, message = "Некорректный год")
    @Max(value = 2022, message = "Некорректный год")
    private int year_of_birth;

    private List<Book> books;

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

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }


    public Human() {
    }

    public Human(String name, int year_of_birth) {
        this.name = name;
        this.year_of_birth = year_of_birth;
    }

}
