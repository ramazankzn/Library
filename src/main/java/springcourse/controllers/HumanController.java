package springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.HumanDAO;
import springcourse.models.Human;
import springcourse.util.HumanValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/humans")
public class HumanController {

    private final HumanDAO humanDAO;
    private final HumanValidator humanValidator;

    @Autowired
    public HumanController(HumanDAO humanDAO, HumanValidator humanValidator) {
        this.humanDAO = humanDAO;
        this.humanValidator = humanValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("humans", humanDAO.index());
        return "humans/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("human", humanDAO.show(id));
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
//        humanValidator.validate(human, bindingResult);
//        if (bindingResult.hasErrors()) return "humans/edit";
        humanDAO.update(id, human);
        return "redirect:/humans";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        humanDAO.delete(id);
        return "redirect:/humans";
    }
//Не рабочий
    @GetMapping("{id}/getBooks")
    public String getBooks(Model model, @PathVariable("id") int id) {
        model.addAttribute("human", id);
        return "humans/edit";
    }
}
