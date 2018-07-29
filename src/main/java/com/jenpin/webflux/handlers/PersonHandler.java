package com.jenpin.webflux.handlers;/**
 * Created by Administrator on 2018/7/29.
 */

import com.jenpin.webflux.dao.PersonRepository;
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

    private final PersonRepository personRepository;

    @Autowired
    public PersonHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Long> save(Person person) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(personRepository.save(person)));
    }

    public Mono<Person> findPersonById(Long id) {
        return Mono.justOrEmpty(personRepository.findPersonById(id));
    }

    public Flux<Person> findAllPerson() {
        return Flux.fromIterable(personRepository.findAll());
    }

    public Mono<Long> modifyPerson(Person city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(personRepository.updatePerson(city)));
    }

    public Mono<Long> deletePerson(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(personRepository.deletePerson(id)));
    }
}
