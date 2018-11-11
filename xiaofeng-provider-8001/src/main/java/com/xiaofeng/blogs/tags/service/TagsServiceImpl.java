package com.xiaofeng.blogs.tags.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:04
 * @Description:
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Resource
    private TagsRepository tagsRepository;

    @Override
    public Integer add(TagsEntity tagsEntity) {
        return tagsRepository.add(tagsEntity);
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        return tagsRepository.delete(id, userId);
    }

    @Override
    public Integer update(TagsEntity tagsEntity) {
        return tagsRepository.update(tagsEntity);
    }

    @Override
    public Integer incr(Integer id, Integer userId) {
        return tagsRepository.incr(id, userId);
    }

    @Override
    public TagsEntity getTagById(Integer id) {
        return tagsRepository.getTagById(id);
    }

    @Override
    public List<TagsEntity> getTagsByUserId(Integer userId, Integer pageNum, Integer pageSize, String tagName) {
        List<TagsEntity> list = PageHelper.startPage(pageNum, pageSize);
        tagsRepository.getTagsByUserId(userId, tagName);
        System.out.println(list);
        return list;
    }
}
