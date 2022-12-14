package springcourse.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Book;
import springcourse.models.Human;

import java.util.List;
import java.util.Optional;

@Component
public class HumanDAO {
    private final JdbcTemplate jdbcTemplate;

    public HumanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Human> index() {

        return jdbcTemplate.query("SELECT * FROM Humans", new HumanMapper());
    }

    public List<Human> peopleNames() {

        return jdbcTemplate.query("SELECT name FROM Humans", new HumanMapper());
    }

    public Human show(int id) {

        return jdbcTemplate.query("SELECT * FROM Humans WHERE human_id=?", new Object[]{id}, new HumanMapper())
                .stream().findAny().orElse(null);
    }

    public Human show(String name) {
        return jdbcTemplate.query("SELECT * FROM Humans WHERE name = ?", new Object[]{name}, new HumanMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Human human) {
        jdbcTemplate.update("INSERT INTO Humans(name, year_of_birth) VALUES(?, ?)",
                human.getName(), human.getYear_of_birth());
    }

    public void update(int id, Human human) {
        jdbcTemplate.update("UPDATE Humans SET name = ?, year_of_birth = ? WHERE human_id=?",
                human.getName(), human.getYear_of_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Humans WHERE human_id = ?", id);
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("SELECT Books.* FROM Books JOIN Humans ON Books.owner_id = humans.human_id WHERE humans.human_id = ?", new Object[]{id}, new BookMapper());
    }

    public void assign(int id, Book book) {
        jdbcTemplate.update("UPDATE books SET owner_id = ? WHERE book_id = ?", id, book.getBook_id());
    }


}