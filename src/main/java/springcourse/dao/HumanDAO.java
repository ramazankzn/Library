package springcourse.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Human;

import java.util.List;

@Component
public class HumanDAO {
    private final JdbcTemplate jdbcTemplate;

    public HumanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Human> index() {

        return jdbcTemplate.query("SELECT * FROM Humans", new HumanMapper());
    }

    public Human show(int id) {

        return jdbcTemplate.query("SELECT * FROM Humans WHERE human_id=?", new Object[]{id}, new HumanMapper())
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
        jdbcTemplate.update("DELETE FROM Humans WHERE id = ?", id);
    }

    public void getBooks(int id) {
        jdbcTemplate.query("SELECT Books.* FROM Books JOIN Humans ON Books.owner_id = humans.human_id WHERE humans.human_id = ?", new Object[]{id}, new HumanMapper());
    }


}