package ru.job4j.accident.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.*;

import java.util.*;

@Service
@Transactional
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final TypeRepository typeRepository;
    private final RuleRepository ruleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthorityRepository authorityRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           TypeRepository typeRepository,
                           RuleRepository ruleRepository,
                           UserRepository userRepository,
                           PasswordEncoder encoder,
                           AuthorityRepository authorityRepository) {
        this.accidentRepository = accidentRepository;
        this.typeRepository = typeRepository;
        this.ruleRepository = ruleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authorityRepository = authorityRepository;
    }

    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    public List<AccidentType> getTypes() {
        return (List<AccidentType>) typeRepository.findAll();
    }

    public List<Rule> getRules() {
        return (List<Rule>) ruleRepository.findAll();
    }

    public void save(Accident newAccident, String[] rIds) {
        if (rIds == null) {
            update(newAccident);
        } else {
            create(newAccident, rIds);
        }
        accidentRepository.save(newAccident);
    }

    private void create(Accident newAccident, String[] rIds) {
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = Arrays.asList(rIds);
        stringList.forEach(str -> integerList.add(Integer.parseInt(str)));
        Iterable<Rule> rules = ruleRepository.findAllById(integerList);
        rules.forEach(newAccident::addRule);
    }

    private void update(Accident newAccident) {
        int id = newAccident.getId();
        Optional<Accident> accidentOptional = accidentRepository.findById(id);
        Set<Rule> rules = accidentOptional.orElseThrow().getRules();
        newAccident.setRules(rules);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public void reg(User user) throws Exception {
        String name = user.getUsername();
        Optional<User> optionalUser = userRepository.findUserByUsername(name);
        if (optionalUser.isPresent()) {
            throw new Exception("Registration Error! User already exists!");
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
            userRepository.save(user);
        }
    }
}
