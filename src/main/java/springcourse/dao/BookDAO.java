package springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Book;
import springcourse.models.Human;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;


    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BookMapper()) ;
    }

    public Book show(int book_id) {

        return jdbcTemplate.query("SELECT * FROM books WHERE book_id=?", new Object[]{book_id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO books(name, author, year_of_production) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear_of_production());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE books Set name = ?, author = ?, year_of_production = ? WHERE book_id = ?",
                book.getName(), book.getAuthor(), book.getYear_of_production(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE book_id = ?", id);
    }

    public void getOwner(Book book) {
        jdbcTemplate.query("SELECT Humans.* FROM Books JOIN Humans ON Books.owner_id = Humans.human_id WHERE Books.book_id = ?",
                new Object[]{book.getBook_id()}, new BookMapper()).stream().findAny();
    }
    public void assign(Human human, Book book) {
        jdbcTemplate.update("UPDATE books SET owner_id = ? WHERE book_id = ?", human.getHuman_id(), book.getBook_id());
    }

}
