package ru.job4j.accident.controller;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {

    private AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        accidentService = new AccidentService(new AccidentMem());
        model.addAttribute("accidents", accidentService.getAccidents());
        return "index";
    }
}
