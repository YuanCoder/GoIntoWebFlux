package com.jenpin.webflux.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Jenpin
 * @date: 2018/7/28 0028 17:33
 * @email: yuan_268311@163.com
 * @description: 模型
 **/
@Getter
@Setter
@ToString
public class Person {

    private String name;

    private Integer age;

    private Long tel;

    private String address;
}
