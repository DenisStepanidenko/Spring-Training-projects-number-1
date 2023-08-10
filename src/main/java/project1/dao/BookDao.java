package project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project1.models.Book;

import java.util.List;

@Component
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> allBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name , author , year_of_publication) VALUES(?,?,?)", book.getName(), book.getAuthor(), book.getYearOfPublication());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name = ? , author = ? , year_of_publication = ? WHERE id = ?", book.getName(), book.getAuthor(), book.getYearOfPublication(), id);
    }

    public boolean checkForPerson(int id) {
        List<Book> book = jdbcTemplate.query("SELECT person_id FROM Book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        return book.get(0).getPersonId() == null;
    }

    public void addPerson(int id, int idBook) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", id, idBook);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    public List<Book> showPerson(int id) {
        return jdbcTemplate.query("SELECT person_id FROM Book WHERE id =?", new Object[]{id} , new BeanPropertyRowMapper<>(Book.class));
    }
    public void deletePerson(int id){
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?" , id);
    }
    public void checkForBook(int id){
        // здесь проверяем есть ли какие-то
    }
}
