package project1.dao;

import org.springframework.jdbc.core.RowMapper;
import project1.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFio(resultSet.getString("fio"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));

        return person;
    }
}
