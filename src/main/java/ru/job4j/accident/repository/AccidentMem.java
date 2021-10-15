package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private Integer key = 0;

    public AccidentMem() {
        Accident accident = new Accident(0, "Alarm", "Wow!",
                "Los Angels");
        accidents.put(0, accident);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void save(Accident accident) {
        accidents.put(++key, accident);
    }
}
