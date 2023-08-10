package project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project1.dao.BookDao;
import project1.dao.PersonDao;
import project1.models.Book;
import project1.models.BookAndPerson;
import project1.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private PersonDao personDao;

    @GetMapping()
    public String allBooks(Model model) {
        model.addAttribute("books", bookDao.allBooks());
        return "books/allBooks";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookPage(Model model, @PathVariable("id") int id) {
        // нужно проверить, взял ли какой-нибудь человек эту книгу
        boolean flag = bookDao.checkForPerson(id);
        if (flag) {
            // значит книгу ещё никто не взял
            BookAndPerson bookAndPerson = new BookAndPerson(bookDao, personDao);
            bookAndPerson.setBook(id);
            bookAndPerson.setAllPerson();
            model.addAttribute("bookAndPerson", bookAndPerson);
            return "books/bookWithoutPerson";
        }
        BookAndPerson bookAndPerson = new BookAndPerson(bookDao, personDao);
        bookAndPerson.setBook(id);
        bookAndPerson.setAllPerson(id);
        model.addAttribute("bookAndPerson", bookAndPerson);
        return "books/bookWithPerson";
    }

    @PatchMapping("/addPerson/{id}")
    public String addPerson(@ModelAttribute("bookAndPerson") BookAndPerson bookAndPerson, @PathVariable("id") int idBook) {
        // в bookAndPerson хранится id человека, которому мы назначаем книгу
        bookDao.addPerson(bookAndPerson.getId(), idBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.deleteBook(id);
        return "redirect:/books";
    }
    @PatchMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable("id") int id){
        bookDao.deletePerson(id);
        return "redirect:/books";
    }

}
