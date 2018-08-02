package com.jenpin.webflux.controller;/**
 * Created by Administrator on 2018/8/2.
 */

import com.jenpin.webflux.model.Person;
import com.jenpin.webflux.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/8/2 22:35
 * @email: yuan_268311@163.com
 * @description: 个人信息管理controller
 **/
@Controller
@RequestMapping(value = "/person")
public class PersonController {
    private static final String PERSON_FORM_PATH_NAME = "personForm";
    private static final String PERSON_LIST_PATH_NAME = "personList";
    private static final String REDIRECT_TO_PERSON_URL = "redirect:/person";
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public String getpersonList(final Model model) {
        model.addAttribute("personList", personService.findAll());
        return PERSON_LIST_PATH_NAME;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createpersonForm(final Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("action", "create");
        return PERSON_FORM_PATH_NAME;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute Person person) {
        LOGGER.info("PersonController::save 收到参数："+person.toString());
        personService.insertByPerson(person);
        return REDIRECT_TO_PERSON_URL;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getPerson(@PathVariable Long id, final Model model) {
        LOGGER.info("PersonController::getPerson 收到参数："+id);
        final Mono<Person> person = personService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("action", "update");
        return PERSON_FORM_PATH_NAME;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Person person) {
        LOGGER.info("PersonController::update 收到参数："+person.toString());
        personService.update(person);
        return REDIRECT_TO_PERSON_URL;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable Long id) {
        LOGGER.info("PersonController::deletePerson 收到参数："+id);
        personService.delete(id);
        return PERSON_LIST_PATH_NAME;
    }
}
