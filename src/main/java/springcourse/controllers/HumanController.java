package springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.BookDAO;
import springcourse.dao.HumanDAO;
import springcourse.models.Book;
import springcourse.models.Human;
import springcourse.util.HumanValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/humans")
public class HumanController {

    private final HumanDAO humanDAO;
    private final BookDAO bookDAO;
    private final HumanValidator humanValidator;

    @Autowired
    public HumanController(HumanDAO humanDAO, BookDAO bookDAO, HumanValidator humanValidator) {
        this.humanDAO = humanDAO;
        this.bookDAO = bookDAO;
        this.humanValidator = humanValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("humans", humanDAO.index());
        return "humans/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("bookWithoutOwner")Book booksWithoutOwner) {
        model.addAttribute("human", humanDAO.show(id));
        model.addAttribute("books", humanDAO.getBooks(id));
        model.addAttribute("booksWithoutOwner", bookDAO.booksWithoutOwner());
        return "humans/show";
    }

    @GetMapping("/new")
    public String newHuman(@ModelAttribute("human") Human human) {
        return "humans/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("human") @Valid Human human, BindingResult bindingResult) {
        humanValidator.validate(human, bindingResult);
        if (bindingResult.hasErrors()) return "humans/new";
        humanDAO.save(human);
        return "redirect:/humans";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("human", humanDAO.show(id));
        return "humans/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("human") @Valid Human human, BindingResult bindingResult, @PathVariable("id") int id) {
        humanValidator.validate(human, bindingResult);
        if (bindingResult.hasErrors()) return "humans/edit";
        humanDAO.update(id, human);
        return "redirect:/humans";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        humanDAO.delete(id);
        return "redirect:/humans";
    }

    @GetMapping("{id}/getBooks")
    public String getBooks(Model model, @PathVariable("id") int id) {
        model.addAttribute("books", humanDAO.getBooks(id));
        return "redirect:/humans/{id}/show";
    }

    @PatchMapping("/{id}/assign")
    public String selectBook(@PathVariable("id") int id, @ModelAttribute("book") Book selectedBook) {
        humanDAO.assign(id, selectedBook);
        return "redirect:/humans/" + id;
    }


}
