package com.xiaofeng.blogs.health.service;

import com.xiaofeng.blogs.health.repository.HealthRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 22:07
 * @Description:
 */
@Service
public class HealthServiceImpl implements HealthService {

    @Resource
    private HealthRepository healthRepository;

    @Override
    public void check() {
        healthRepository.check();
    }
}
