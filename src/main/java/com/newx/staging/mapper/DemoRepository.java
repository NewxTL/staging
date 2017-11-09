package com.newx.staging.mapper;

import com.newx.staging.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Newx on 2017/11/7.
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
    public Demo findByName(String name);
}
