package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> getAccidents() {
        return accidentJdbcTemplate.getAccidents();
    }

    public List<AccidentType> getTypes() {
        return accidentJdbcTemplate.getTypes();
    }

    public List<Rule> getRules() {
        return accidentJdbcTemplate.getRules();
    }

    public void save(Accident accident, String[] rIds) {
        accidentJdbcTemplate.save(accident, rIds);
    }

    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }
}
