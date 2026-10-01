package com.shuanca.peopleadministrator.controller;

import com.shuanca.peopleadministrator.model.Person;
import com.shuanca.peopleadministrator.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"","/"})
public class ViewController {

    @Autowired
    PersonServiceImpl service;

    @GetMapping("/personCreate")
    public String personCreate(Model model) {
        model.addAttribute("person", new Person());
        return "person_create";
    }

    @PostMapping("/doPersonCreate")
    public String doPersonCreate(Person person, RedirectAttributes redirectAttributes) {
        if (service.create(person) != null) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/personCreate";
    }

    @GetMapping({"", "/"})
    public String viewPersons(Model model) {
        model.addAttribute("people", service.getAll());
        return "person_list";
    }

    @GetMapping("/personEdit/{id}")
    public String personEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("person", service.getById(id));
        return "person_edit";
    }

    @PostMapping("/doPersonEdit")
    public String doPersonEdit(Person person, RedirectAttributes redirectAttributes) {
        if (service.update(person.getId(), person) != null) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/personEdit/" + person.getId();
    }

    @GetMapping("/personDelete/{id}")
    public String personDelete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (service.delete(id) != null) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/";
    }
}


