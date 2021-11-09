package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<Accident> findById(int id) {
        return jdbc.query("select distinct a.id, a.name, a.text, a.address " +
                        "from accident a left join type on a.type_id=type.id " +
                        "left join accident_rules ar on a.id = ar.accident_id " +
                        "left join rule r on ar.rules_id = r.id where a.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Accident.class)
        ).stream().findAny();
    }

    public void save(Accident accident, String[] rIds) {
        if (accident.getId() == 0) {
            create(accident, rIds);
        } else {
            update(accident);
        }
    }

    private void create(Accident accident, String[] rIds) {
        jdbc.update("insert into accident (name, text, address, type_id) " +
                        "values (?,?,?,?) ",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId());
        for (String rId : rIds) {
            jdbc.update("insert into accident_rules (accident_id, rules_id) " +
                    "values (currval('accident_id_seq'),?)", Integer.parseInt(rId));
        }
    }

    private void update(Accident accident) {
        jdbc.update("update accident set name=?, text=?, address=? " +
                        "where id=?", accident.getName(),
                accident.getText(), accident.getAddress(),
                accident.getId());
    }

    public Collection<Accident> getAccidents() {
        return jdbc.query("select distinct a.id, a.name, a.text, a.address " +
                        "from accident a left join type on a.type_id=type.id " +
                        "left join accident_rules ar on a.id = ar.accident_id " +
                        "left join rule r on ar.rules_id = r.id",
                new BeanPropertyRowMapper<>(Accident.class));
    }

    public List<AccidentType> getTypes() {
        return jdbc.query("select id, name from type ",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public List<Rule> getRules() {
        return jdbc.query("select id, name from rule ",
                new BeanPropertyRowMapper<>(Rule.class));
    }
}
