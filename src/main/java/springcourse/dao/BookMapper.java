package springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import springcourse.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBook_id(resultSet.getInt("book_id"));

        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setYear_of_production(resultSet.getInt("year_of_production"));

        return book;
    }
}
