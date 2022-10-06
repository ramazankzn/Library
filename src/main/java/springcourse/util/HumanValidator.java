package springcourse.util;


import springcourse.dao.HumanDAO;
import springcourse.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HumanValidator implements Validator {

    private final HumanDAO humanDAO;

    @Autowired
    public HumanValidator(HumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Human human = (Human) target;
        if (humanDAO.show(human.getName())!= null) {
            errors.rejectValue("name", "", "Имя должно быть уникальным");
        }
    }
}
