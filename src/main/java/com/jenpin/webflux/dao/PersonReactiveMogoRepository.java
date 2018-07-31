package com.jenpin.webflux.dao;

import com.jenpin.webflux.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Jenpin
 * @date: 2018/7/31 0031 16:42
 * @email: yuan_268311@163.com
 * @description: 响应式mogodb 数据层
 **/
@Repository
public interface PersonReactiveMogoRepository extends ReactiveMongoRepository<Person , Long> {
}