package com.jenpin.webflux.handlers;

import com.jenpin.webflux.dao.PersonCollectionRepository;
import com.jenpin.webflux.dao.PersonReactiveMogoRepository;
import com.jenpin.webflux.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/7/29 11:28
 * @email: yuan_268311@163.com
 * @description: PersonHandlder 处理器类Handler
 **/
@Component
public class PersonReactiveHandler {

    private final PersonReactiveMogoRepository personRepository;

    @Autowired
    public PersonReactiveHandler(PersonReactiveMogoRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }

    public Mono<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Flux<Person> findAllPerson() {
        return personRepository.findAll();
    }

    public Mono<Person> modifyPerson(Person person) {
        return personRepository.save(person);
    }

    public Mono<Long> deletePerson(Long id) {
        personRepository.deleteById(id);
        return Mono.create(personMonoSink ->personMonoSink.success(id));
    }
}
