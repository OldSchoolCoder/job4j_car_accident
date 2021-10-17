package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    AtomicInteger counter = new AtomicInteger();

    public AccidentMem() {
        Accident accident = new Accident(0, "Alarm", "Wow!",
                "Los Angels");
        accidents.put(0, accident);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void save(Accident accident) {
        int id = counter.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }
}
