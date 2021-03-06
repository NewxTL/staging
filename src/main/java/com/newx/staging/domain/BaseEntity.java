package com.newx.staging.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 该类为实体超类，并不会映射到具体的表，但该超类的子类所对应的表都必须含有该类中的属性字段
 * Created by Newx on 2017/11/29.
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime lastUpdate;

}
