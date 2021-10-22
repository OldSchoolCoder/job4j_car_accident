package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public void save(Accident accident, String[] rIds) {
        accidentMem.save(accident, rIds);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }
}
