package com.jenpin.webflux.controller;

import com.jenpin.webflux.handlers.PersonReactiveHandler;
import com.jenpin.webflux.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/7/29 12:18
 * @email: yuan_268311@163.com
 * @description:
 **/
@RestController
@RequestMapping(value = "/mogo/person")
public class PersonReactiveWebFluxController {

        @Autowired
        private PersonReactiveHandler personHandler;
        @GetMapping(value = "/{id}")
        public Mono<Person> findPersionById(@PathVariable("id") Long id) {
            return personHandler.findPersonById(id);
        }
        @GetMapping()
        public Flux<Person> findAllPersion() {
            return personHandler.findAllPerson();
        }
        @PostMapping()
        public Mono<Person> savePersion(@RequestBody Person persion) {
            return personHandler.save(persion);
        }
        @PutMapping()
        public Mono<Person> modifyPersion(@RequestBody Person persion) {
            return personHandler.modifyPerson(persion);
        }
        @DeleteMapping(value = "/{id}")
        public Mono<Long> deletePersion(@PathVariable("id") Long id) {
            return personHandler.deletePerson(id);
        }

}
