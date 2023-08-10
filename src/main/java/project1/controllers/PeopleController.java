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
import project1.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private PersonValidator personValidator;

    @GetMapping()
    public String allPeople(Model model) {
        model.addAttribute("people", personDao.allPeople());
        return "people/showAllPeople";
    }

    @GetMapping("/{id}")
    public String personPage(Model model, @PathVariable("id") int id) {
        /*
         *** Создадим bookList который показывает все книги у person с данным id
         */
        List<Book> bookList = personDao.checkForBook(id);
        if (bookList.isEmpty()) {
            /*
             *** Сюда попадаем, если книг у пользователя нет
             */
            model.addAttribute("person", personDao.show(id));
            return "people/personWithoutBook";
        }

        BookAndPerson bookAndPerson = new BookAndPerson(bookDao , personDao);
        bookAndPerson.setPerson(personDao.show(id));
        bookAndPerson.setAllBook(bookList);
        model.addAttribute("bookAndPerson" , bookAndPerson);
        return "people/personWithBook";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDao.update(id, person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDao.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.deletePerson(id);
        return "redirect:/people";
    }

}
