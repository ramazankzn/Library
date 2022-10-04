package springcourse.util;


import springcourse.dao.HumanDAO;
import springcourse.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HumanValidator implements Validator {

    private final HumanDAO personDAO;

    @Autowired
    public HumanValidator(HumanDAO humanDAO) {
        this.personDAO = humanDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Human person = (Human) target;
//        if (personDAO.show(person.getYear()).isPresent()) {
//            errors.rejectValue("email", "", "This email is already taken");
//        }
    }
}
