package com.jenpin.webflux.handlers;

import com.jenpin.webflux.dao.PersonCollectionRepository;
import com.jenpin.webflux.dao.PersonReactiveMogoRepository;
import com.jenpin.webflux.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReactiveHandler.class);
    private final PersonReactiveMogoRepository personRepository;

    @Autowired private RedisTemplate redisTemplate;

    @Autowired
    public PersonReactiveHandler(PersonReactiveMogoRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }

    public Mono<Person> findPersonById(Long id) {
        // 从缓存中获取城市信息
        String key = "person_" + id;
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            Person person = operations.get(key);
            LOGGER.info("PersonReactiveHandler.findPersonById() : 从缓存中获取了城市 >> " + person.toString());
            return Mono.create(personMonoSink -> personMonoSink.success(person));
        }
        // 从 MongoDB 中获取城市信息
        Mono<Person> personMono = personRepository.findById(id);

        if (personMono == null)
            return personMono;
        // 插入缓存
        personMono.subscribe(personObj -> {
            operations.set(key, personObj);
            LOGGER.info("PersonReactiveHandler.findPersonById() : person插入缓存 >> " + personObj.toString());
        });
        return personMono;
    }

    public Flux<Person> findAllPerson() {
        return personRepository.findAll().cache();
    }

    public Mono<Person> modifyPerson(Person person) {
        // 缓存存在，删除缓存
        String key = "person_" + person.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("PersonReactiveHandler.modifyPerson() : 从缓存中删除person ID >> " + person.getId());
        }
        return personRepository.save(person).cache();
    }

    public Mono<Long> deletePerson(Long id) {
        // 缓存存在，删除缓存
        String key = "person_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("PersonReactiveHandler.deletePerson() : 从缓存中删除person ID >> " + id);
        }
        personRepository.deleteById(id);
        return Mono.create(personMonoSink -> personMonoSink.success(id));
    }

    public Mono<Person> findByPersonName(String name){
        return personRepository.findByName(name);
    }
}
