package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    //@Transactional
    public Accident save(Accident accident, String[] rIds) {
        try (Session session = sf.openSession()) {
            //Rule rule = new Rule();
            //Rule rule = Rule.of(5,"R5");
            Rule rule = session.get(Rule.class, Integer.parseInt(rIds[0]));
            //session.save(rule);
            //session.save(session.load(Rule.class,4));
            //rule.setId(1);
            accident.addRule(rule);
            //accident.addRule(session.load(Rule.class,4));
//            HashSet<Rule> rules = new HashSet<>();
//            accident.setRules(rules);
            //session.save(rule);
            session.save(accident);
            return accident;
        }
    }

    public Collection<Accident> getAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct a from Accident a " +
                    "left join fetch a.rules", Accident.class).list();
        }
    }

    public List<AccidentType> getTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType ",
                    AccidentType.class).list();
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule ",
                    Rule.class).list();
        }
    }
}
