package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AccidentControl {

    private static final Logger LOGGER = Logger.getLogger(AccidentControl
            .class.getName());

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = accidentService.getTypes();
        model.addAttribute("types", types);
        List<Rule> rules = accidentService.getRules();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.save(accident, rIds);
        return "redirect:/";
    }
//
//    @GetMapping("/update")
//    public String update(@RequestParam("id") int id, Model model)
//            throws Exception {
//        Optional<Accident> accidentOptional = accidentService.findById(id);
//        if (accidentOptional.isPresent()) {
//            model.addAttribute("accident", accidentOptional.get());
//        } else {
//            throw new Exception("Error! Accident not found!");
//        }
//        return "accident/update";
//    }
}
