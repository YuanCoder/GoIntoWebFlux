package com.jenpin.webflux.handlers;

import com.jenpin.webflux.dao.PersonCollectionRepository;
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
public class PersonHandler {

    private final PersonCollectionRepository personRepository;

    @Autowired
    public PersonHandler(PersonCollectionRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Long> save(Person person) {
        return Mono.create(personMonoSink -> personMonoSink.success(personRepository.save(person)));
    }

    public Mono<Person> findPersonById(Long id) {
        return Mono.justOrEmpty(personRepository.findPersonById(id));
    }

    public Flux<Person> findAllPerson() {
        return Flux.fromIterable(personRepository.findAll());
    }

    public Mono<Long> modifyPerson(Person person) {
        return Mono.create(personMonoSink -> personMonoSink.success(personRepository.updatePerson(person)));
    }

    public Mono<Long> deletePerson(Long id) {
        return Mono.create(personMonoSink -> personMonoSink.success(personRepository.deletePerson(id)));
    }
}
