package springcourse.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import springcourse.dao.BookDAO;
import springcourse.models.Book;

public class BookValidator implements Validator {

    BookDAO bookDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (bookDAO.show(book.getName())!= null) {
            errors.rejectValue("name", "", "Имя должно быть уникальным");
        }
    }
}
