package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> listItems = new ArrayList<>();
        listItems.add("Item 1");
        listItems.add("Item 2");
        model.addAttribute("listItems", listItems);
        return "index";
    }
}
