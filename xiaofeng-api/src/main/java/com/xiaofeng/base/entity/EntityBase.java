package com.xiaofeng.base.entity;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 21:55
 * @Description: 实体转DTO
 */
@Log4j2
public class EntityBase<T, X> {

    public T entityToDto(Class<T> targetClass){
        try {
            T dto = targetClass.newInstance();
            BeanUtils.copyProperties(this, dto);
            return dto;
        } catch (Exception e) {
            log.error("EntityBase.entityToDto is failed！！！return null", e);
            e.printStackTrace();
        }
        return null;
    }

    public List<T> entitysToDtos(List<X> sources, Class<T> targetClass){
        try {
            List<T> dtoList = JSON.parseArray(JSON.toJSONString(sources), targetClass);
            return dtoList;
        } catch (Exception e) {
            log.error("EntityBase.entitysToDtos is failed！！！return null", e);
            e.printStackTrace();
        }
        return null;
    }

}
