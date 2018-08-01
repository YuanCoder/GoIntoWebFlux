package com.jenpin.webflux.controller;

import com.jenpin.webflux.handlers.PersonReactiveHandler;
import com.jenpin.webflux.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/7/29 12:18
 * @email: yuan_268311@163.com
 * @description:
 **/
@Controller
@RequestMapping(value = "/mogo/person")
public class PersonReactiveWebFluxController {

    private static final String PERSON_LIST_PATH_NAME = "persons";
    private static final String  PERSON_PATH = "say";

        @Autowired
        private PersonReactiveHandler personHandler;

        @GetMapping(value = "/{id}")
        @ResponseBody
        public Mono<Person> findPersionById(@PathVariable("id") Long id) {
            return personHandler.findPersonById(id);
        }

        @GetMapping()
        @ResponseBody
        public Flux<Person> findAllPersion() {
            return personHandler.findAllPerson();
        }

        @PostMapping()
        @ResponseBody
        public Mono<Person> savePersion(@RequestBody Person persion) {
            return personHandler.save(persion);
        }

        @PutMapping()
        @ResponseBody
        public Mono<Person> modifyPersion(@RequestBody Person persion) {
            return personHandler.modifyPerson(persion);
        }

        @DeleteMapping(value = "/{id}")
        public Mono<Long> deletePersion(@PathVariable("id") Long id) {
            return personHandler.deletePerson(id);
        }

        @GetMapping("/say")
        public Mono<String> selfIntroduction(final Model model) {
            final Mono<Person> person = personHandler.findPersonById(1L);
            model.addAttribute("person",person);
            return Mono.create(monoSink -> monoSink.success(PERSON_PATH));
        }

        @GetMapping("/list")
        public String listPage(final Model model) {
            final Flux<Person> personList = personHandler.findAllPerson();
            model.addAttribute("personList", personList);
            return PERSON_LIST_PATH_NAME;
        }

    @GetMapping("/search")
    public String getByCityName(final Model model, @RequestParam("name") String name) {
        final Mono<Person> person = personHandler.findByPersonName(name);
        model.addAttribute("person", person);
        return PERSON_PATH;
    }
}
