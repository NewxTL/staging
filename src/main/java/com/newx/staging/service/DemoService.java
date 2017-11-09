package com.newx.staging.service;

import com.newx.staging.config.TargetDataSource;
import com.newx.staging.domain.Demo;
import com.newx.staging.mapper.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Newx on 2017/11/7.
 */
@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public void ds1Insert(String name, String age){
        Demo demo = new Demo();
        demo.setName(name);
        demo.setAge(age);
        demoRepository.save(demo);
    }

    public void ds1Update(String name, String age){
        Demo demo = demoRepository.findByName(name);
        demo.setName(name);
        demo.setAge(age);
        demoRepository.save(demo);
    }

    public void ds1delete(String name){
        Demo demo = demoRepository.findByName(name);
        demoRepository.delete(demo);
    }

    public Demo ds1Query(String name){
        Demo demo = demoRepository.findByName(name);
        return demo;
    }

    @TargetDataSource(name = "ds2")
    public void ds2Insert(String name, String age){
        Demo demo = new Demo();
        demo.setName(name);
        demo.setAge(age);
        demoRepository.save(demo);
    }

    @TargetDataSource(name = "ds2")
    public void ds2Update(String name, String age){
        Demo demo = demoRepository.findByName(name);
        demo.setName(name);
        demo.setAge(age);
        demoRepository.save(demo);
    }

    @TargetDataSource(name = "ds2")
    public void ds2delete(String name){
        Demo demo = demoRepository.findByName(name);
        demoRepository.delete(demo);
    }

    @TargetDataSource(name = "ds2")
    public Demo ds2Query(String name){
        Demo demo = demoRepository.findByName(name);
        return demo;
    }
}
