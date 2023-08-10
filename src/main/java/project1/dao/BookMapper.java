package project1.dao;

import org.springframework.jdbc.core.RowMapper;
import project1.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfPublication(resultSet.getInt("year_of_publication"));
        book.setPersonId(resultSet.getInt("person_id"));

        return book;
    }
}
