package ru.job4j.accident.controller;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.accident");
        context.refresh();
        AccidentMem accidentMem = context.getBean(AccidentMem.class);
        model.addAttribute("accidents", accidentMem.getAccidents());
        return "index";
    }
}
