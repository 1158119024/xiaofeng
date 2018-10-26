package com.xiaofeng.config.health;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xiaofeng.blogs.health.service.HealthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @auther: 晓枫
 * @date: 2018/10/17 22:11
 * @Description: 健康检查
 */
@Log4j2
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Autowired
    private HealthService healthService;

    //错误次数
    private int healthIndicatorErrorCount;
    //
    private int healthIndicatorCount = 2;
    //是否错误
    private boolean hasError = false;

    @Override
    @HystrixCommand(fallbackMethod = "connectDataBaseNo")
    public Health health() {
        log.info("health check");
        try{
            healthService.check();
            //查询通过后重置错误次数, 状态修改为up
            if( healthIndicatorErrorCount >= healthIndicatorCount ){
                healthIndicatorErrorCount = 0;
            }
        }catch (Exception e){
            healthIndicatorErrorCount++;
            log.error("health check: databases connection time out, connection count = "+healthIndicatorErrorCount);
        }
        if( healthIndicatorErrorCount >= healthIndicatorCount ){
            return new Health.Builder(Status.DOWN).build();
        }
        return new Health.Builder(Status.UP).build();
    }

    public Health connectDataBaseNo(){
        return new Health.Builder(Status.DOWN).build();
    }

}
