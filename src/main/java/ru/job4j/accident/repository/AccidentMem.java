package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import ru.job4j.accident.controller.AccidentControl;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class AccidentMem {

    private static final Logger LOGGER = Logger.getLogger(AccidentMem
            .class.getName());
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();
    private final List<AccidentType> types = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    public AccidentMem() {
        types.add(AccidentType.of(0, "Two cars"));
        types.add(AccidentType.of(1, "Car and human"));
        types.add(AccidentType.of(2, "Car and bike"));
        rules.add(Rule.of(0, "Rule. 1"));
        rules.add(Rule.of(1, "Rule. 2"));
        rules.add(Rule.of(2, "Rule. 3"));
    }

    public List<AccidentType> getTypes() {
        return types;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void save(Accident accident, String[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String rId : rIds) {
            ruleSet.add(rules.get(Integer.parseInt(rId)));
        }
        accident.setRules(ruleSet);
        int typeId = accident.getType().getId();
        AccidentType type = types.get(typeId);
        accident.setType(type);
        if (accident.getId() == 0) {
            accident.setId(counter.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
