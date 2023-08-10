package project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project1.dao.PersonDao;
import project1.models.Person;

@Component
public class PersonValidator implements Validator {
    @Autowired
    private PersonDao personDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(personDao.show(person.getFio()).isPresent()){
            errors.rejectValue("fio" , "" , "Данное ФИО уже создано");
        }
    }


}
