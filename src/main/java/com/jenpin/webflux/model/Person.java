package com.jenpin.webflux.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author: Jenpin
 * @date: 2018/7/28 0028 17:33
 * @email: yuan_268311@163.com
 * @description: 模型
 **/
@Getter
@Setter
@ToString
public class Person implements Serializable {
    /**
     * @Id 注解标记对应库表的主键或者唯⼀标识符。
     */
    @Id
    private Long id;

    private String name;

    private Integer age;

    private Long tel;

    private String address;
}
