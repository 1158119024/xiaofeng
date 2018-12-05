package com.xiaofeng.blogs.collect.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.blogs.collect.repository.CollectRepository;

import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.service.TagsService;
import com.xiaofeng.blogs.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private TagsService tagsService;

    @Override
    public List<CollectDto> list() {
        List<CollectEntity> entities = collectRepository.list();
        List<CollectDto> collectDtos = new CollectEntity().entitysToDtos(entities, CollectDto.class);
        return collectDtos;
    }

    @Override
    public Integer add(CollectEntity collectEntity) {
        Integer result = collectRepository.add(collectEntity);
        return result;
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
    public PageInfo<CollectDto> getCollectsByCondition(CollectBo collectBo) {
        List<CollectEntity> list = PageHelper.startPage(collectBo.getPageNum(), collectBo.getPageSize());
        collectRepository.getCollectsByCondition(collectBo);
        // 塞入page信息
        PageInfo pageInfo = new PageInfo(list);

        List<CollectDto> collectDtos = new ArrayList<>();
        for( CollectEntity entity: list ){
            String tagId = entity.getTagId();
            CollectDto collectDto = entity.entityToDto(CollectDto.class);
            if( !StringUtils.isEmpty(tagId) ){
                collectDto.setTagsEntity(tagsService.getTagById(Integer.parseInt(tagId)));
            }
            collectDtos.add(collectDto);
        }
        // 替换里面的list
        pageInfo.setList(collectDtos);
        return pageInfo;
    }


}
