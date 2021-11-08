package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    //@Transactional
    public void save(Accident accident, String[] rIds) {
//        if (accident.getId() == 0) {
//            create(accident, rIds);
//        } else {
//            update(accident);
//        }
        Session session = sf.getCurrentSession();
        //Rule rule = Rule.of(6,"R6");
        //Rule rule = session.load(Rule.class, 3);
        Rule rule = session.find(Rule.class, 2);
//        for (String rId: rIds) {
//            //Rule rule = session.get(Rule.class, Integer.parseInt(rId));
//            Rule rule = session.load(Rule.class, Integer.parseInt(rId));
//            //accident.addRule(rule);
//        }
        accident.addRule(rule); //not
        session.save(accident);
        //session.merge(accident);
        //session.persist(accident);
        //accident.addRule(rule); work but not joinTable
//        return accident;
    }

    private void update(Accident accident) {

    }

    private void create(Accident accident, String[] rIds) {
        Session session = sf.getCurrentSession();
        for (String rId: rIds) {
            Rule rule = session.get(Rule.class, Integer.parseInt(rId));
            accident.addRule(rule);
        }
        session.save(accident);
    }

    public Collection<Accident> getAccidents() {
        //try (Session session = sf.openSession()) {
        Session session = sf.getCurrentSession();
            return session.createQuery("select distinct a from Accident a " +
                    "left join fetch a.rules", Accident.class).list();
        //}
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
