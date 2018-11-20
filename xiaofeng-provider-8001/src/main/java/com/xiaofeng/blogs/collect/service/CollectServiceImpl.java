package com.xiaofeng.blogs.collect.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.collect.bo.CollectBo;
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

    @Override
    public Integer add(CollectEntity collectEntity) {
        return collectRepository.add(collectEntity);
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        return collectRepository.delete(id, userId);
    }

    @Override
    public Integer update(CollectEntity collectEntity) {
        return collectRepository.update(collectEntity);
    }

    @Override
    public List<CollectEntity> getCollectsByCondition(CollectBo collectBo) {
        List<CollectEntity> list = PageHelper.startPage(collectBo.getPageNum(), collectBo.getPageSize());
        collectRepository.getCollectsByCondition(collectBo);
        return list;
    }


}
