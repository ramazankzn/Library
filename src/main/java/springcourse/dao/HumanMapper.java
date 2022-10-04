package springcourse.dao;

import springcourse.models.Human;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HumanMapper implements RowMapper<Human> {
    @Override
    public Human mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Human human = new Human();
        human.setHuman_id(resultSet.getInt("human_id"));
        human.setName(resultSet.getString("name"));
        human.setYear(resultSet.getInt("year_of_birth"));

        return human;
    }
}
