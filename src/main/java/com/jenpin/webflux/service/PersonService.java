package com.jenpin.webflux.service;/**
 * Created by Administrator on 2018/8/2.
 */

import com.jenpin.webflux.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/8/2 21:11
 * @email: yuan_268311@163.com
 * @description:
 **/
public interface PersonService {
    Flux<Person> findAll();

    Mono<Person> insertByPerson(Person person);

    Mono<Person> update(Person person);

    Mono<Void> delete(Long id);

    Mono<Person> findById(Long id);
}
