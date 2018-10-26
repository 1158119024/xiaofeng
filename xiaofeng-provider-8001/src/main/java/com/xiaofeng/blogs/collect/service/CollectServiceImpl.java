package com.xiaofeng.blogs.collect.service;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.blogs.collect.repository.CollectRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 17:03
 * @Description:
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectRepository collectRepository;

    @Override
    public List<CollectDto> list() {
        List<CollectEntity> entities = collectRepository.list();
        List<CollectDto> collectDtos = new CollectEntity().entitysToDtos(entities, CollectDto.class);
        return collectDtos;
    }




}
