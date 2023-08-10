package project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project1.models.Book;
import project1.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> allPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio = ?", new Object[]{fio}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fio = ? , year_of_birth = ? WHERE id = ?", person.getFio(), person.getYearOfBirth(), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, year_of_birth) VALUES(?,?)", person.getFio(), person.getYearOfBirth());
    }
    public List<Book> checkForBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?" , new Object[]{id} , new BeanPropertyRowMapper<>(Book.class));

    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?" , id);
    }


}
