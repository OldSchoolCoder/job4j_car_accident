package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import java.util.*;

@Service
@Transactional
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final TypeRepository typeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           TypeRepository typeRepository,
                           RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.typeRepository = typeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    public List<AccidentType> getTypes() {
        return (List<AccidentType>) typeRepository.findAll();
    }

    public List<Rule> getRules() {
        return (List<Rule>) ruleRepository.findAll();
    }

    public void save(Accident newAccident, String[] rIds) {
        if (rIds == null) {
            update(newAccident);
        } else {
            create(newAccident, rIds);
        }
        accidentRepository.save(newAccident);
    }

    private void create(Accident newAccident, String[] rIds) {
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = Arrays.asList(rIds);
        stringList.forEach(str -> integerList.add(Integer.parseInt(str)));
        Iterable<Rule> rules = ruleRepository.findAllById(integerList);
        rules.forEach(newAccident::addRule);
    }

    private void update(Accident newAccident) {
        int id = newAccident.getId();
        Optional<Accident> accidentOptional = accidentRepository.findById(id);
        Set<Rule> rules = accidentOptional.orElseThrow().getRules();
        newAccident.setRules(rules);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
