package springcourse.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Human;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
                human.getName(), human.getYear());
    }

    public void update(int id, Human human) {
        jdbcTemplate.update("UPDATE Humans SET name = ?, year_of_birth = ? WHERE human_id=?",
                human.getName(), human.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Humans WHERE id = ?", id);

    }

}