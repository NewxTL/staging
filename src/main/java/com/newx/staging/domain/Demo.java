package com.newx.staging.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类的属性字段使用驼峰命名法，就可以直接映射到数据库字段
 * 如userId映射数据库user_id字段
 * Created by Newx on 2017/11/7.
 */
@Entity
@Table(name = "demo")
@Data
public class Demo extends BaseEntity{

    @Column
    private String name;

    @Column
    private String age;

}
