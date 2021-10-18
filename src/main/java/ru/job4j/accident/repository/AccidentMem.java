package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    AtomicInteger counter = new AtomicInteger();

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(counter.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
