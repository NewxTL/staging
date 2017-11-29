package com.newx.staging.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类的属性字段使用驼峰命名法，就可以直接映射到数据库字段
 * 如userId映射数据库user_id字段
 * Created by Newx on 2017/11/7.
 */
@Entity
@Table(name = "demo")
public class Demo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
