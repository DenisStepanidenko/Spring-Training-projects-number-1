package project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    private int id;
    @NotEmpty(message = "Данное поле не может быть пустым")
    private String name;
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+" , message = "Данное поле должно соответствовать формату Имя Фамилия")
    private String author;

    @Min(value = 1 , message = "Год издания не может быть отрицательным/равным нулю")
    private int yearOfPublication;
    private Integer personId;

    public Book(int id, String name, String author, int yearOfPublication, Integer personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.personId = personId;
    }
    public Book(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer  personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", personId=" + personId +
                '}';
    }
}
