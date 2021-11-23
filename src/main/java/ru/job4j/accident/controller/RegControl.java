package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccidentService;

@Controller
public class RegControl {

    private final AccidentService accidentService;

    public RegControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) throws Exception {
        accidentService.reg(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
