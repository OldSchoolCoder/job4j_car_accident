package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @Transactional
    public Collection<Accident> getAccidents() {
        return accidentHibernate.getAccidents();
    }

    @Transactional
    public List<AccidentType> getTypes() {
        return accidentHibernate.getTypes();
    }

    @Transactional
    public List<Rule> getRules() {
        return accidentHibernate.getRules();
    }

    @Transactional
    public void save(Accident accident, String[] rIds) {
        accidentHibernate.save(accident, rIds);
    }

    @Transactional
    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }
}
