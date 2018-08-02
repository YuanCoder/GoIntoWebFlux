package com.jenpin.webflux.service.impl;/**
 * Created by Administrator on 2018/8/2.
 */

import com.jenpin.webflux.dao.PersonReactiveMogoRepository;
import com.jenpin.webflux.model.Person;
import com.jenpin.webflux.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/8/2 21:57
 * @email: yuan_268311@163.com
 * @description:
 **/
@Component
public class PersonServiceImpl implements PersonService{

    private final PersonReactiveMogoRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonReactiveMogoRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<Person> insertByPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<Person> update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return personRepository.deleteById(id);
    }

    @Override
    public Mono<Person> findById(Long id) {
        return personRepository.findById(id);
    }
}
