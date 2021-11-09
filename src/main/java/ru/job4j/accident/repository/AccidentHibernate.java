package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void save(Accident accident, String[] rIds) {
        if (accident.getId() == 0) {
            create(accident, rIds);
        } else {
            update(accident);
        }
    }

    private void update(Accident newAccident) {
        Session session = sf.getCurrentSession();
        Accident oldAccident = session.find(Accident.class, newAccident.getId());
        newAccident.setRules(oldAccident.getRules());
        session.merge(newAccident);
    }

    private void create(Accident accident, String[] rIds) {
        Session session = sf.getCurrentSession();
        for (String rId : rIds) {
            Rule rule = session.find(Rule.class, Integer.parseInt(rId));
            accident.addRule(rule);
        }
        session.save(accident);
    }

    public Collection<Accident> getAccidents() {
        Session session = sf.getCurrentSession();
        return session.createQuery("select distinct a from Accident a " +
                "left join fetch a.rules", Accident.class).list();
    }

    public List<AccidentType> getTypes() {
        Session session = sf.getCurrentSession();
        return session.createQuery("from AccidentType ",
                AccidentType.class).list();
    }

    public List<Rule> getRules() {
        Session session = sf.getCurrentSession();
        return session.createQuery("from Rule ", Rule.class).list();
    }

    public Optional<Accident> findById(int id) {
        Session session = sf.getCurrentSession();
        final Query query = session.createQuery("select distinct a from " +
                "Accident a left join fetch a.rules where a.id = :id")
                .setParameter("id", id);
        return query.uniqueResultOptional();
    }
}
