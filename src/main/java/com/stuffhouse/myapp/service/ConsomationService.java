package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.domain.Compteur;
import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.repository.ConsomationRepository;
import com.stuffhouse.myapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ConsomationService {

    private final ConsomationRepository consomationRepository;
    private final PersonRepository personRepository;
    private final StockService stockService;
    private final CaisseService caisseService;
    private final PersonService personService;
    private final ArticleService articleService;


    public ConsomationService(ConsomationRepository consomationRepository, PersonRepository personRepository, StockService stockService, CaisseService caisseService, PersonService personService, ArticleService articleService) {
        this.consomationRepository = consomationRepository;
        this.personRepository = personRepository;


        this.stockService = stockService;

        this.caisseService = caisseService;
        this.personService = personService;
        this.articleService = articleService;
    }


    public Consomation insertConsomationData(Consomation consomation) {

        Optional<Article> article = articleService.getArticleInformationById(consomation.getArticle().getId());

        double ValueToPayCalcul = consomation.getQuantity() * article.get().getPrix();

        stockService.updateStock(consomation.getArticle(), consomation.getQuantity(), "-");
        if (consomation.getPaid()) {
            caisseService.updateCaisseIfConsomationPaid("615e3266d5f0b54a6ba4a249", ValueToPayCalcul);

        } else {

            personService.updatePersonCreditUsingId(personService.getPersonByCode(consomation.getCode()).getId(), ValueToPayCalcul);
        }

        Consomation consomationfinal = Consomation.builder()
            .article(consomation.getArticle())
            .quantity(consomation.getQuantity())
            .person(personService.getPersonByCode(consomation.getCode()))
            .paid(consomation.getPaid())
            .valueToPay(ValueToPayCalcul)
            .code(consomation.getCode())
            .build();

        return consomationRepository.insert(consomationfinal);
    }

    public Collection<Consomation> getAllConsomationInformation() {
        return consomationRepository.findAll();
    }

    public Optional<Consomation> getConsomationInformationById(String id) {
        return consomationRepository.findById(id);
    }

    public void deleteConsomationUsingId(String id) {
        try {
            consomationRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public double getCredits() {

        Compteur c = new Compteur();
        personRepository.findAll().forEach(a -> {
            c.setD(c.getD() + a.getCredit());

        });


        return c.getD();
    }

    public Consomation updateConsomationIfPaid(Consomation consomation) {
        return consomationRepository.save(consomation);
    }

    public List<Consomation> getConsomationsByCode(String code) {
        return consomationRepository.findConsomationsByCode(code);
    }

    public Person updatePersonCreditIfPayCredit(String code) {
        Optional<Person> findPersonQuery = Optional.ofNullable(personRepository.getPersonByCode(code));
        Person personValues = findPersonQuery.get();


        getConsomationsByCode(personValues.getCode()).forEach(consomation -> {
            if(!consomation.getPaid())
                consomation.setPaid(true);
           updateConsomationIfPaid(consomation);


        });
        caisseService.updateCaisseIfConsomationPaid("615e3266d5f0b54a6ba4a249",personValues.getCredit());
        personValues.setCredit(0);
        return personRepository.save(personValues);
    }


}
