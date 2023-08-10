package project1.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project1.dao.BookDao;
import project1.dao.PersonDao;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookAndPerson {

    private PersonDao personDao;

    private BookDao bookDao;
    private Integer id;

    public BookAndPerson(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Book book;
    private List<Person> allPerson;
    private List<Book> allBook;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Book> getAllBook() {
        return allBook;
    }

    public void setAllBook(List<Book> allBook) {
        this.allBook = allBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(int id) {
        Book bookCurrent = new Book();
        //System.out.println(bookDao.show(id).getName());
        bookCurrent.setName(bookDao.show(id).getName());
        bookCurrent.setAuthor(bookDao.show(id).getAuthor());
        bookCurrent.setYearOfPublication(bookDao.show(id).getYearOfPublication());
        bookCurrent.setId(bookDao.show(id).getId());
        bookCurrent.setPersonId(null);
        this.book = bookCurrent;
    }

    public List<Person> getAllPerson() {
        return allPerson;
    }

    public void setAllPerson() {
        this.allPerson = personDao.allPeople();
    }

    public void setAllPerson(int id) {
        List<Book> current = bookDao.showPerson(id);
        id = current.get(0).getPersonId();
        Person person = new Person();
        person.setFio(personDao.show(id).getFio());
        person.setYearOfBirth(personDao.show(id).getYearOfBirth());
        person.setId(personDao.show(id).getId());
        allPerson = new ArrayList<>();
        allPerson.add(person);
    }
}
