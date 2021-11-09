package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccidentService {

    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Collection<Accident> getAccidents() {
        return accidentHibernate.getAccidents();
    }

    public List<AccidentType> getTypes() {
        return accidentHibernate.getTypes();
    }

    public List<Rule> getRules() {
        return accidentHibernate.getRules();
    }

    public void save(Accident accident, String[] rIds) {
        accidentHibernate.save(accident, rIds);
    }

    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }
}
